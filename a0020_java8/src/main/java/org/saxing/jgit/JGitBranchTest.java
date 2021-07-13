package org.saxing.jgit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JGitBranchTest {

    /**
     * 查看分支提交者
     * git log --oneline 【branch name】 | cut -d " " -f 1 | tail -1 | xargs git log
     *
     * @param args
     */
    public static void main(String[] args) {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider("xxxxx", "xxxxx");

        Map<String, String> projects = new HashMap<String, String>() {
            private static final long serialVersionUID = -8852265700453513109L;

            {
                // java
                put("demo", "D:/code/au/0code-branch-detect/demo/");
                put("demob", "D:/code/au/0code-branch-detect/demob/");
                put("gateway", "D:/code/au/0code-branch-detect/gateway/");
                put("user", "D:/code/au/0code-branch-detect/user/");
                put("message", "D:/code/au/0code-branch-detect/message/");
                put("kid-arch", "D:/code/au/0code-branch-detect/kid-arch/");
                put("attendance", "D:/code/au/0code-branch-detect/attendance/");
                put("garden", "D:/code/au/0code-branch-detect/garden/");
                put("album", "D:/code/au/0code-branch-detect/album/");
                put("file", "D:/code/au/0code-branch-detect/file/");
                put("iam", "D:/code/au/0code-branch-detect/iam/");
                put("test", "D:/code/au/0code-branch-detect/test/");
                put("moment", "D:/code/au/0code-branch-detect/moment/");
                put("statistic", "D:/code/au/0code-branch-detect/statistic/");
                put("boss", "D:/code/au/0code-branch-detect/boss/");
                put("finance", "D:/code/au/0code-branch-detect/finance/");
                put("log", "D:/code/au/0code-branch-detect/log/");
                put("javaphpcms", "D:/code/au/0code-branch-detect/javaphpcms/");
                put("kms", "D:/code/au/0code-branch-detect/kms/");
                put("visitor", "D:/code/au/0code-branch-detect/visitor/");
                put("audit", "D:/code/au/0code-branch-detect/audit/");
                put("growth", "D:/code/au/0code-branch-detect/growth/");
                put("patrol", "D:/code/au/0code-branch-detect/patrol/");
                put("moralism", "D:/code/au/0code-branch-detect/moralism/");
                put("workflow", "D:/code/au/0code-branch-detect/workflow/");

                // web


                // android


                // ios
            }
        };
        Set<String> keySet = projects.keySet();
        List<String> result = new ArrayList<>();




        keySet.forEach(key -> {
            String path = projects.get(key);
            System.out.println("项目名：" + key);
            List<String> remoteBranch = getRemoteBranch(path, cp);
            remoteBranch.forEach(System.out::println);
            final Integer[] num = {0};
            remoteBranch.forEach(branch -> {
                if (branch.startsWith("refs/remotes/origin/HEAD")
                        || branch.startsWith("refs/heads/develop")
                        || branch.startsWith("refs/heads/master")

                ) {
                    return;
                }
                branch = branch.replaceAll("refs/remotes/origin/", "");
                result.add(branch);
                num[0]++;
            });
            if (num[0] > 0) {
                result.add("项目名：" + key);
                result.add("");
                result.add("");
            }
        });
        try {
            FileUtils.writeLines(new File("branch.txt"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static List<String> getRemoteBranch(String path, CredentialsProvider cp)  {
        try {



            Repository repo = new FileRepository(path + ".git");
            Git git = new Git(repo);


            List<RefSpec> specs = new ArrayList<RefSpec>();
            specs.add(new RefSpec("+" + Constants.R_HEADS + "*:" + Constants.R_REMOTES + Constants.DEFAULT_REMOTE_NAME + "/*"));
//            specs.add(new RefSpec("+" + Constants.R_NOTES + "*:" + Constants.R_NOTES + "*"));
//            specs.add(new RefSpec("+" + Constants.R_TAGS + "*:" + Constants.R_TAGS + "*"));

            FetchResult call = git.fetch().setCheckFetchedObjects(true)
                    .setDryRun(true)
                    .setRefSpecs(specs)
                    .setCredentialsProvider(cp).call();
            System.out.println(call);

            List<String> branchNames = new ArrayList<>();
            Collection<Ref> advertisedRefs = call.getAdvertisedRefs();
            if (CollectionUtils.isNotEmpty(advertisedRefs)) {
                advertisedRefs.forEach(ref -> {
                    if (ref.getName().startsWith("refs/heads/")) {
                        branchNames.add(ref.getName());
                    }
                });
            }
//            List<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.REMOTE).call();
//            if (CollectionUtils.isNotEmpty(branches)){
//                return branches.stream().map(Ref::getName).collect(Collectors.toList());
//            }
            return branchNames;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
