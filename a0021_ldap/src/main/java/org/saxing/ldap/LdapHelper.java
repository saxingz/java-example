package org.saxing.ldap;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LdapHelper {
    private final String URL = "ldap://listen.net:389/";
    private final String BASEDN = "dc=listen,dc=net"; // 根据自己情况进行修改
    private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private LdapContext ctx = null;
    private final Control[] connCtls = null;

    private void LDAP_connect() {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL + BASEDN);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        String root = "ldap"; //根据自己情况修改
        env.put(Context.SECURITY_PRINCIPAL, root); // 管理员
        env.put(Context.SECURITY_CREDENTIALS, "abcd@1234"); // 管理员密码

        try {
            ctx = new InitialLdapContext(env, connCtls);
            System.out.println( "连接成功" );

        } catch (javax.naming.AuthenticationException e) {
            System.out.println("连接失败：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("连接出错：");
            e.printStackTrace();
        }

    }
    private void closeContext(){
        if (ctx != null) {
            try {
                ctx.close();
            }
            catch (NamingException e) {
                e.printStackTrace();
            }

        }
    }
    private String getUserDN(String uid) {
        String userDN = "";
        LDAP_connect();
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            NamingEnumeration<SearchResult> en = ctx.search("", "uid=" + uid, constraints);

            if (en == null || !en.hasMoreElements()) {
                System.out.println("未找到该用户");
            }
// maybe more than one element
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    userDN += si.getName();
                    userDN += "," + BASEDN;
                } else {
                    System.out.println(obj);
                }
            }
        } catch (Exception e) {
            System.out.println("查找用户时产生异常。");
            e.printStackTrace();
        }

        return userDN;
    }

    public boolean authenricate(String UID, String password) {
        boolean valide = false;
        String userDN = getUserDN(UID);

        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(connCtls);
            System.out.println(userDN + " 验证通过");
            valide = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " 验证失败");
            System.out.println(e.toString());
            valide = false;
        } catch (NamingException e) {
            System.out.println(userDN + " 验证失败");
            valide = false;
        }
        closeContext();
        return valide;
    }


    public boolean addUser(String usr, String pwd,String uid,String description) {
        try {
            LDAP_connect();
            BasicAttributes attrsbu = new BasicAttributes();
            BasicAttribute objclassSet = new BasicAttribute("objectclass");
            objclassSet.add("inetOrgPerson");
            attrsbu.put(objclassSet);
            attrsbu.put("sn", usr);
            attrsbu.put("cn", usr);
            attrsbu.put("uid", uid);
            attrsbu.put("userPassword", pwd);
            attrsbu.put("description", description);
            ctx.createSubcontext("uid="+uid+"", attrsbu);

            return true;
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        closeContext();
        return false;
    }

    //测试
    public static void main(String[] args) {
        LdapHelper ldap = new LdapHelper();

//ldap.LDAP_connect();

        if(ldap.authenricate("sx00518", "Saxing1213")){

            System.out.println( "该用户认证成功" );

        }
//ldap.addUser("yorker","secret");

    }
}