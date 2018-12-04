package org.saxing.gatewaycodegenerate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeGen {

    public static void main(String[] args) throws IOException {
        // 平台
        List<String[]> platforms = new ArrayList<>();
        platforms.add(new String[]{"web", "domainWeb"});
        platforms.add(new String[]{"open", "domainOpen"});
        platforms.add(new String[]{"boss", "domainBoss"});

        // 系统
        List<String> systems = new ArrayList<>();
        systems.add("iam");
        systems.add("user");
        systems.add("crm");
        systems.add("boss");

        List<String> domains = new ArrayList<>();
        domains.add("domainWeb");
        domains.add("domainOpen");
        domains.add("domainBoss");

        // 人员
        int maxNum = 10;

        writeToTxt(platforms, systems, domains, maxNum);



    }

    private static String getSxNumber(int i){
        if (i >= 0 && i < 10){
            return "sx0000" + i;
        }else if (i >= 10 && i < 100){
            return "sx000" + i;
        }else if (i >= 100 && i < 1000){
            return "sx00" + i;
        }else if (i >= 1000 && i < 10000){
            return "sx0" + i;
        }else{
            return "sx" + i;
        }
    }

    private static void writeToTxt(List<String[]> platforms, List<String> systems, List<String> domains, int maxNum) throws IOException {

        File file = new File("Gatewayconfig.txt");

        // 创建基于文件的输出流
        FileOutputStream fos = new FileOutputStream(file);

        fos.write((
                "import org.springframework.beans.factory.annotation.Value;\n" +
                "import org.springframework.cloud.gateway.route.RouteLocator;\n" +
                "import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "/**\n" +
                " * gateway 配置 本代码由代码生成\n" +
                " * \n" +
                " * @author saxing 2018/12/1 15:43\n" +
                " */\n" +
                "@Configuration\n" +
                "public class GateWayConfig {\n" +
                "\n" +
                "    @Value(\"${domain.web:}\")\n" +
                "    private String domainWeb;\n" +
                "\n" +
                "    @Value(\"${domain.open:}\")\n" +
                "    private String domainOpen;\n" +
                "\n" +
                "    @Value(\"${domain.in:}\")\n" +
                "    private String domainBoss;\n" +
                "\n" +
                "    @Value(\"${gateway.port:80}\")\n" +
                "    private String serverPort;\n" +
                "\n" +
                "    @Value(\"${spring.profiles.active:DEV}\")\n" +
                "    private String profile;\n" +
                "\n" +
                "    /**\n" +
                "     * debug mode配置\n" +
                "     *\n" +
                "     * web平台前端页面加上header，key,value值一样: {debugger}-{plat}-{system},\n" +
                "     * 如 liuhan-web-iam:liuhan-web-iam ,\n" +
                "     *\n" +
                "     * @param builder\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Bean\n" +
                "    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){").getBytes());

        for (int i = 0; i < maxNum; i++) {
            fos.write(("String " + getSxNumber(i) + " =  \""+ getSxNumber(i) +"\";\n").getBytes());
        }

        fos.write("\n".getBytes());

        for (int i = 0; i < maxNum; i++) {
            for (String plat[] : platforms){
                for (String system : systems){
                    //sx00518WebIam
                    fos.write(("DebugMode " + getSxNumber(i) +  plat[0] + system + " = new DebugMode("+getSxNumber(i)+", " + "\"" + plat[0] + "\"" + ", " + "\"" +  system + "\"" +  ", " + plat[1] + ", serverPort); \n ").getBytes());
                }
            }
        }

        fos.write("\n".getBytes());


        fos.write(("if (!\"DEV\".equalsIgnoreCase(profile)){\n" +
                "            return builder.routes().build();\n" +
                "        } \n").getBytes());

        fos.write("return builder.routes() \n".getBytes());

        for (int i = 0; i < maxNum; i++) {
            for (String plat[] : platforms){
                for (String system : systems){
                    String currentName = getSxNumber(i) +  plat[0] + system;
                    fos.write((".route(r -> r.path(\"/\"+" + currentName + ".getSystem()+\"/**\")" +
                            "                        .and().header(" + currentName + ".getHeader(), " + currentName + ".getHeader())" +
                            "                        .and().host(" + currentName + ".getDomain() + \":\" + " + currentName + ".getPort())" +
                            "                        .filters(f -> f.rewritePath(\"/\" + " + currentName + ".getSystem() + \"/(?<segment>.*)\", \"/${segment}\"))" +
                            "                        .uri(" + currentName + ".getLb())" +
                            "                        .id(" + currentName + ".getId())) \n").getBytes());

                }
            }
        }

        fos.write("\n".getBytes());

        fos.write(".build();}.getBytes()".getBytes());

        fos.write(("/**\n" +
                "     * 调试模式\n" +
                "     * \n" +
                "     * @author saxing 2018/12/2 17:06\n" +
                "     */\n" +
                "    class DebugMode{\n" +
                "        //{liuhan, platWeb, systemIam, domainWeb, serverPort}\n" +
                "        private String debugger;\n" +
                "        private String plat;\n" +
                "        private String system;\n" +
                "        private String domain;\n" +
                "        private String port;\n" +
                "        private String header;\n" +
                "        private String id;\n" +
                "        private String lb;\n" +
                "\n" +
                "        public DebugMode(String debugger, String plat, String system, String domain, String port) {\n" +
                "            this.debugger = debugger;\n" +
                "            this.plat = plat;\n" +
                "            this.system = system;\n" +
                "            this.domain = domain;\n" +
                "            this.port = port;\n" +
                "            header = debugger + \"-\" + plat + \"-\" + system;\n" +
                "            id = plat + \"-\" + system + header;\n" +
                "            lb = \"lb://\" + system + \"-\" + debugger;\n" +
                "        }\n" +
                "\n" +
                "        public String getDebugger() {\n" +
                "            return debugger;\n" +
                "        }\n" +
                "\n" +
                "        public String getPlat() {\n" +
                "            return plat;\n" +
                "        }\n" +
                "\n" +
                "        public String getSystem() {\n" +
                "            return system;\n" +
                "        }\n" +
                "\n" +
                "        public String getDomain() {\n" +
                "            return domain;\n" +
                "        }\n" +
                "\n" +
                "        public String getPort() {\n" +
                "            return port;\n" +
                "        }\n" +
                "\n" +
                "        public String getHeader() {\n" +
                "            return header;\n" +
                "        }\n" +
                "\n" +
                "        public String getId() {\n" +
                "            return id;\n" +
                "        }\n" +
                "\n" +
                "        public String getLb() {\n" +
                "            return lb;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}").getBytes());



        // 关闭输出流
        fos.close();
        System.out.println("输入完成");
    }

}
