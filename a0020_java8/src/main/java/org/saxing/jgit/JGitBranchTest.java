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
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider("au000748", "xxxx");

        Map<String, String> projects = new HashMap<String, String>() {
            private static final long serialVersionUID = -8852265700453513109L;

            {
                /******************
                 * java
                 *****************/
                // demo项目
                put("java-demo", "D:/code/au/0code-branch-detect/java/demo/");
                // demob项目
                put("java-demob", "D:/code/au/0code-branch-detect/java/demob/");
                // 网关项目
                put("java-gateway", "D:/code/au/0code-branch-detect/java/gateway/");
                // 用户中心
                put("java-user", "D:/code/au/0code-branch-detect/java/user/");
                // 消息中心
                put("java-message", "D:/code/au/0code-branch-detect/java/message/");
                // 7kid基础体系
                put("java-kid-arch", "D:/code/au/0code-branch-dejava/tect/kid-arch/");
                // 考勤健康
                put("java-attendance", "D:/code/au/0code-branch-detect/java/attendance/");
                // 园务系统
                put("java-garden", "D:/code/au/0code-branch-detect/java/garden/");
                // 智能相册
                put("java-album", "D:/code/au/0code-branch-detect/java/album/");
                // 文件系统
                put("java-file", "D:/code/au/0code-branch-detect/java/file/");
                // 鉴权中心
                put("java-iam", "D:/code/au/0code-branch-detect/java/iam/");
                // 内测项目
                put("java-test", "D:/code/au/0code-branch-detect/java/test/");
                // 班级圈
                put("java-moment", "D:/code/au/0code-branch-detect/java/moment/");
                // 统计项目
                put("java-statistic", "D:/code/au/0code-branch-detect/java/statistic/");
                // 运营后台
                put("java-boss", "D:/code/au/0code-branch-detect/java/boss/");
                // 财务系统
                put("java-finance", "D:/code/au/0code-branch-detect/java/finance/");
                // 日志系统
                put("java-log", "D:/code/au/0code-branch-detect/java/log/");
                // php cms系统
                put("java-javaphpcms", "D:/code/au/0code-branch-detect/java/javaphpcms/");
                // 密钥系统
                put("java-kms", "D:/code/au/0code-branch-detect/java/kms/");
                // 访客系统
                put("java-visitor", "D:/code/au/0code-branch-detect/java/visitor/");
                // 审计系统
                put("java-audit", "D:/code/au/0code-branch-detect/java/audit/");
                // 成长档案
                put("java-growth", "D:/code/au/0code-branch-detect/java/growth/");
                // 巡检系统
                put("java-patrol", "D:/code/au/0code-branch-detect/java/patrol/");
                // 德育中心
                put("java-moralism", "D:/code/au/0code-branch-detect/java/moralism/");
                // 工作流系统
                put("java-workflow", "D:/code/au/0code-branch-detect/java/workflow/");
                // 第三方对接平台
                put("java-third", "D:/code/au/0code-branch-detect/java/third/");
                // 数据模拟系统
                put("java-faker", "D:/code/au/0code-branch-detect/java/faker/");
                // MQ消息队列平台
                put("java-mq", "D:/code/au/0code-branch-detect/java/mq/");

                /******************
                 * web
                 *****************/
                // 阿优宝宝
                put("web-7kid_aubaby", "D:/code/au/0code-branch-detect/web/7kid_aubaby/");
                // 班牌
                put("web-7kid_classboard", "D:/code/au/0code-branch-detect/web/7kid_classboard/");
                // 企web-业后台
                put("7kid_company", "D:/code/au/0code-branch-detect/web/7kid_company/");
                // 移动端项目
                put("web-7kid_h5", "D:/code/au/0code-branch-detect/web/7kid_h5/");
                // 内嵌网页
                put("web-7kid_hybrid", "D:/code/au/0code-branch-detect/web/7kid_hybrid/");
                // 园所后台
                put("web-7kid_manager", "D:/code/au/0code-branch-detect/web/7kid_manager/");
                // 运营后台
                put("web-7kid_operation", "D:/code/au/0code-branch-detect/web/7kid_operation/");
                // 监管后台
                put("web-7kid_regulation", "D:/code/au/0code-branch-detect/web/7kid_regulation/");
                // 静态页面
                put("web-staticpages", "D:/code/au/0code-branch-detect/web/staticpages/");
                // 统一登录页面
                put("iamweb", "D:/code/au/0code-branch-detect/web/staticpages/");
                // 7kid 基于 vue-cli 5.0.1 的基础项目模板
                put("7kid_web_template ", "D:/code/au/0code-branch-detect/web/7kid_base/");
                // 7kid web 家长端
                put("7kid_web_parent", "D:/code/au/0code-branch-detect/web/7kid_web_parent/");
                // 7kid web 教师端
                put("7kid_web_teacher", "D:/code/au/0code-branch-detect/web/7kid_web_teacher/");
                // 7kid web 硬件端
                put("7kid_web_device", "D:/code/au/0code-branch-detect/web/7kid_web_device/");


                /******************
                 * android
                 *****************/
                // 在线教育
                put("android-androidonlineteaching", "D:/code/au/0code-branch-detect/android/androidonlineteaching/");
                // 家长端
                put("android-androidparent", "D:/code/au/0code-branch-detect/android/androidparent/");
                // 教师端
                put("android-androidteacher", "D:/code/au/0code-branch-detect/android/androidteacher/");
                // 工具类
                put("android-androidutils", "D:/code/au/0code-branch-detect/android/androidutils/");
                // core project
                put("android-aucoreproject", "D:/code/au/0code-branch-detect/android/aucoreproject/");
                // basedata
                put("android-basedata", "D:/code/au/0code-branch-detect/android/basedata/");
                // EasyPhotos
                put("android-EasyPhotos", "D:/code/au/0code-branch-detect/android/EasyPhotos/");
                // eshop
                put("android-eshop", "D:/code/au/0code-branch-detect/android/eshop/");
                // hardwareau
                put("android-hardwareau", "D:/code/au/0code-branch-detect/android/hardwareau/");
                // httputil
                put("android-httputil", "D:/code/au/0code-branch-detect/android/httputil/");
                // lzyimagepicker
                put("android-lzyimagepicker", "D:/code/au/0code-branch-detect/android/lzyimagepicker/");
                // MPAndroidChart
                put("android-MPAndroidChart", "D:/code/au/0code-branch-detect/android/MPAndroidChart/");
                // shortvideo
                put("android-shortvideo", "D:/code/au/0code-branch-detect/android/shortvideo/");
                // zxing
                put("android-zxing", "D:/code/au/0code-branch-detect/android/zxing/");


                /******************
                 * ios
                 *****************/
                // 家长端
                put("ios-7kidiosparent", "D:/code/au/0code-branch-detect/ios/7kidiosparent/");
                // 通用工具
                put("ios-aucommontools", "D:/code/au/0code-branch-detect/ios/aucommontools/");
                // aucommonviews
                put("ios-aucommonviews", "D:/code/au/0code-branch-detect/ios/aucommonviews/");
                // auextension
                put("ios-auextension", "D:/code/au/0code-branch-detect/ios/auextension/");
                // auimuikit
                put("ios-auimuikit", "D:/code/au/0code-branch-detect/ios/auimuikit/");
                // aurespos
                put("ios-aurespos", "D:/code/au/0code-branch-detect/ios/aurespos/");
                // ausharesdk
                put("ios-ausharesdk", "D:/code/au/0code-branch-detect/ios/ausharesdk/");
                // authirdloginsdk
                put("ios-authirdloginsdk", "D:/code/au/0code-branch-detect/ios/authirdloginsdk/");
                // AUWebpLib
                put("ios-AUWebpLib", "D:/code/au/0code-branch-detect/ios/AUWebpLib/");
                // inapppurchases
                put("ios-inapppurchases", "D:/code/au/0code-branch-detect/ios/inapppurchases/");
                // iosonlineteach
                put("ios-iosonlineteach", "D:/code/au/0code-branch-detect/ios/iosonlineteach/");
                // iosteacher
                put("ios-iosteacher", "D:/code/au/0code-branch-detect/ios/iosteacher/");
                // schoolshop
                put("ios-schoolshop", "D:/code/au/0code-branch-detect/ios/schoolshop/");

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
