package org.saxing.ldap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.SortControl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LDAPConnector {

    /**
     * Logger for this class and subclasses
     */
    protected final Log log = LogFactory.getLog(getClass());
    private static LDAPConnector instance;
    private String url;
    private String baseDN;
    private String bindDN;
    private String bindPassword;
    private final Hashtable<String, String> env = new Hashtable<String, String>();
    private final Control[] sortConnCtls = new SortControl[1];

    {
        try {
            sortConnCtls[0] = new SortControl("sAMAccountName", Control.CRITICAL);
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) {
        System.out.println(new LDAPConnector().validateUser("xxxx", "xxxxx"));;
        System.out.println("dddddd");
    }

    public boolean validateUser(String username, String password) {
        boolean passed = false;
        LdapContext dirContext = null;
        try {
            // create initial context
            dirContext = new InitialLdapContext(env, sortConnCtls);
            dirContext.setRequestControls(sortConnCtls);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = "(sAMAccountName=" + username + ")";
            NamingEnumeration<?> answer = dirContext.search("", filter, controls);
            String userDN = null;
            if (answer.hasMore()) {
                userDN = ((NameClassPair) answer.nextElement()).getName();
            }
            // set up environment for creating initial context
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.PROVIDER_URL, url + baseDN);
            env.put(Context.SECURITY_PRINCIPAL, userDN + "," + baseDN);
            env.put(Context.SECURITY_CREDENTIALS, password);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put("com.sun.jndi.ldap.connect.timeout", "1000");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            // create initial context
            DirContext context = new InitialDirContext(env);
            passed = true;
            context.close();
        } catch (NamingException e) {
            e.printStackTrace();
            // ignore error
            // e.printStackTrace();
        } finally {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }

        }
        return passed;
    }


    private LDAPConnector() {
        try {
            url = "ldap://xxxx.net:389/";
            baseDN = "dc=xxxx,dc=net";
            bindDN = "ldxxxap";
            bindPassword = "abssscdxxx@xxx12sss34";
            // set up environment for creating initial context  
            env.put(Context.PROVIDER_URL, url + baseDN);
            env.put(Context.SECURITY_PRINCIPAL, bindDN);
            env.put(Context.SECURITY_CREDENTIALS, bindPassword);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put("java.naming.batchsize", "50");
            env.put("com.sun.jndi.ldap.connect.timeout", "3000");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put("com.sun.jndi.ldap.connect.pool", "true");
            // the following pool parameters doesn't work  
            // must setup as java init parameters  
            env.put("com.sun.jndi.ldap.connect.pool.maxsize", "3");
            env.put("com.sun.jndi.ldap.connect.pool.prefsize", "1");
            env.put("com.sun.jndi.ldap.connect.pool.timeout", "300000");
            env.put("com.sun.jndi.ldap.connect.pool.initsize", "1");
            env.put("com.sun.jndi.ldap.connect.pool.authentication", "simple");

        } catch (Exception e) {
            // ignore error  
            e.printStackTrace();
        }
    }

    public static LDAPConnector getInstance() {
        if (instance == null)
            instance = new LDAPConnector();
        return instance;
    }


}
