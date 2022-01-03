package org.saxing.a0037_code_initializr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Initializr {

    public static final String SOURCE_PATH = "D:\\code\\au\\au-initializr";
    public static final String DES_PATH = "D:\\code\\au\\au-initializr-destination";
    public static final String VARIABLE_PROJECT_NAME = "\\{project-name\\}";
    public static final String VARIABLE_ROOT_PACKAGE_NAME = "\\{root-package-name\\}";
    public static final String VARIABLE_DATASOURCE_NAME = "\\{datasource-name\\}";
    public static final String VARIABLE_DESCRIPTION = "\\{project-description\\}";
    public static final String VARIABLE_README_MD = "\\{readme.md\\}";
    public static final String VARIABLE_APOLLO_APPID = "\\{apollo.appid\\}";

    /**
     * 注意 ：
     * 1. 初始化前先到apollo建立项目
     * 2. 添加spring.application.name
     * 3. 添加namespace: common, common-mysql, common-redis, common-consul
     * 4. 建立数据库
     *
     * 5. 类似 kid-arch项目， 在apollo要手动指定以下
     * spring.datasource.name=kidarch
     * 6. gitlab 设置 maintainers， master分支只允许合并，不允许提交.
     *      -- 又改为master可push了，因为很多人会在本地merge
     * 7. gitlab 设置 develop 分支也不允许提交，只允许合并
     *      -- 又改为master可push了，因为很多人会在本地merge
     * 8. 在 log 的apollo中加入存活检测配置
     *
     * see the {@link ProjectList}
     */
    // 待输入
    // public static final String[] A100024 = new String[]{"audit", "审计系统", "100024"};
    static String[] toGenProject =  ProjectList.A100027;

    public static final String PROJECT_NAME = toGenProject[0];
    public static final String PROJECT_ROOT_PACKAGE_NAME = toGenProject[0];
    public static final String PROJECT_DATASOURCE_NAME = PROJECT_ROOT_PACKAGE_NAME;
    public static final String PROJECT_DESCRIPTION = toGenProject[1];
    public static final String PROJECT_README = toGenProject[1];
    public static final String PROJECT_APOLLO_APPID = toGenProject[2];

    public static void main(String[] args) {
        getFile(SOURCE_PATH);
    }

    public static void getFile(String path) {
        // File对象 可以是文件或者目录
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null){
            return;
        }
        for (File curr : files) {
            if (curr.isFile()) {
                System.out.println("^^  " + curr.getName());
                String filePath = curr.getPath();
                changeFile(filePath, getDestinationPath(filePath));
            } else if (curr.isDirectory()) {
                String directoryPath = curr.getPath();
                File temp = new File(getDestinationPath(directoryPath));
                boolean mkdirsRes = temp.mkdirs();
                if (!mkdirsRes) {
                    throw new RuntimeException("directory error");
                }
                getFile(curr.getPath());
            }
        }
    }

    public static String getDestinationPath(String originPath){
        originPath = originPath
                .replaceAll(VARIABLE_PROJECT_NAME, PROJECT_NAME)
                .replaceAll(VARIABLE_ROOT_PACKAGE_NAME, PROJECT_ROOT_PACKAGE_NAME)
                .replaceAll(VARIABLE_DESCRIPTION, PROJECT_DESCRIPTION)
                .replaceAll(VARIABLE_README_MD, PROJECT_README)
                .replaceAll(VARIABLE_DATASOURCE_NAME, PROJECT_DATASOURCE_NAME)
                .replaceAll(VARIABLE_APOLLO_APPID, PROJECT_APOLLO_APPID);
        return DES_PATH + originPath.replaceAll(SOURCE_PATH.replaceAll("\\\\", "\\\\\\\\"), "");
    }

    public static void changeFile(String source, String destination) {
        String line;
        try (
                BufferedReader reader = Files.newBufferedReader(Paths.get(source), StandardCharsets.UTF_8);
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(destination), StandardCharsets.UTF_8);
            ){
            while ((line = reader.readLine()) != null) {
                line = line
                        .replaceAll(VARIABLE_PROJECT_NAME, PROJECT_NAME)
                        .replaceAll(VARIABLE_ROOT_PACKAGE_NAME, PROJECT_ROOT_PACKAGE_NAME)
                        .replaceAll(VARIABLE_DESCRIPTION, PROJECT_DESCRIPTION)
                        .replaceAll(VARIABLE_README_MD, PROJECT_README)
                        .replaceAll(VARIABLE_DATASOURCE_NAME, PROJECT_DATASOURCE_NAME)
                        .replaceAll(VARIABLE_APOLLO_APPID, PROJECT_APOLLO_APPID);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
