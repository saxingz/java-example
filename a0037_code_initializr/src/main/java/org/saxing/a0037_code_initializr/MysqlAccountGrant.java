package org.saxing.a0037_code_initializr;

import java.util.*;

public class MysqlAccountGrant {

    public static void main(String[] args) {
        String[] accountGroup = new String[]{
<<<<<<< HEAD
                "",
=======
                "testaccount",
>>>>>>> origin/master
        };

        String shell = "grant all privileges on *.* to '{accountName}'@'%' identified by '{accountPassword}';\n" +
                "show grants for '{accountName}'@'%';\n" +
                "revoke delete,drop,super on *.* from '{accountName}'@'%';\n" +
                "show grants for '{accountName}'@'%';";

//        String shell = "grant delete on *.* to '{accountName}'@'%' identified by '{accountPassword}';\n" +
//                "show grants for '{accountName}'@'%';\n";

        Map<String, String> record = new TreeMap<>();

        for (String account : accountGroup){
            String password = UUIDUtils.get(15);
            record.put(account, password);

            String result = shell.replaceAll("\\{accountName}", account)
                    .replaceAll("\\{accountPassword}", password);

            System.out.println(result);
            System.out.println("\n");
        }


        System.out.println(record);
    }

}
