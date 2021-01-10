package org.saxing.jgit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * gitlab test
 *
 * @author saxing 2020/12/29 22:35
 */
public class JGitlabTest {

    private static final String tempToken = "mytoken";
    private static final String gitlabHost = "https://xxxx.aukid.net";

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        List<Map<String, Object>> allProject = getAllProject();
        System.out.println("共有项目： " + allProject.size());

        int allCommits = calcCommit(allProject);
        System.out.println("allCommits: " + allCommits);
    }

    private static int calcCommit(List<Map<String, Object>> allProject) throws IOException, ParseException {
        int allCommits = 0;
        final OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();


        for (Map<String, Object> project : allProject) {
            Integer projectId = MapUtils.getInteger(project, "id", 0);
            if (projectId <= 0) {
                continue;
            }
            String url = gitlabHost + "/api/v4/projects/" + projectId + "/repository/commits";
            boolean hasNext = true;
            for (int page = 0; hasNext; page++) {
                HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url))
                        .newBuilder()
                        .addQueryParameter("per_page", "20")
                        .addQueryParameter("page", String.valueOf(page));
                Request request = new Request.Builder()
                        .url(urlBuilder.build())
                        .header("PRIVATE-TOKEN", tempToken)
                        .build();
                Response response = client.newCall(request).execute();
                String resBody = response.body().string();
                List<Map<String, Object>> commits = gson.fromJson(resBody, new TypeToken<List<Map<String, Object>>>() {}.getType());
                if (CollectionUtils.isEmpty(commits)) {
                    hasNext = false;
                    continue;
                }
                for (Map<String, Object> commit : commits) {
                    System.out.println(gson.toJson(commit));
                    String createdAt = MapUtils.getString(commit, "created_at", "");
                    if (StringUtils.isEmpty(createdAt) || createdAt.length() < 1) {
                        continue;
                    }
                    // 2020-04-07T07:38:38.000Z
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
                    Date yearStart = simpleDateFormat.parse("2020-01-01T00:00:00.000Z");
                    Date parseDate = simpleDateFormat.parse(createdAt);
                    if (parseDate.before(yearStart)) {
                        System.out.println("时间小于2020年，不计算 " + gson.toJson(commit));
                        hasNext = false;
                        break;
                    }
                    allCommits++;
                    System.out.println("计算中 commits: " + allCommits);
                }
            }
        }
        return allCommits;
    }

    public static List<Map<String, Object>> getAllProject() throws IOException, InterruptedException {
        List<Map<String, Object>> allProjects = new ArrayList<>();

        final OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();

        String url = gitlabHost + "/api/v4/projects";
        boolean hasNext = true;
        for (int page = 1; hasNext; page++) {
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url))
                    .newBuilder()
                    .addQueryParameter("per_page", "20")
                    .addQueryParameter("page", String.valueOf(page));
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .header("PRIVATE-TOKEN", tempToken)
                    .build();
            Response response = client.newCall(request).execute();
            String resBody = response.body().string();
            List<Map<String, Object>> projects = gson.fromJson(resBody, new TypeToken<List<Map<String, Object>>>() {}.getType());
            if (CollectionUtils.isEmpty(projects)) {
                hasNext = false;
                continue;
            }
            projects.forEach(p -> System.out.println("扫描中:" + gson.toJson(p)));
            allProjects.addAll(projects);
            Thread.sleep(100);
        }
        return allProjects;
    }

}
