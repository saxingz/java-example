package org.saxing.ldap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

public class LdapTest2 {

    private static final Logger logger = LoggerFactory.getLogger(LdapTest2.class);

    public static void main(String[] args) {

        LdapContext ldapContext = new LdapTest2().adLogin("CN=Liu Han,OU=RD,DC=listen,DC=net", "Saxing123");
        System.out.println(ldapContext);
        //
    }

    public LdapContext adLogin(String username, String password) {
        String server = "ldap://listen.net:389";
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            //用户名称，cn,ou,dc 分别：用户，组，域
            env.put(Context.SECURITY_PRINCIPAL, username);
            //用户密码 cn 的密码
            env.put(Context.SECURITY_CREDENTIALS, password);
            //url 格式：协议://ip:端口/组,域   ,直接连接到域或者组上面
            env.put(Context.PROVIDER_URL, server);
            //LDAP 工厂
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            //验证的类型     "none", "simple", "strong"
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            LdapContext ldapContext = new InitialLdapContext(env, null);
            logger.info("ldapContext:" + ldapContext);
            logger.info("用户" + username + "登录验证成功");
            return ldapContext;

        } catch (NamingException e) {
            logger.info("用户" + username + "登录验证失败");
            logger.info("错误信息："+e.getExplanation());
            return null;
        }
    }

}
