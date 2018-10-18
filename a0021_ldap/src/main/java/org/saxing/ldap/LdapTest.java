package org.saxing.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

public class LdapTest {

    public static void main(String[] args) {
        DirContext dirContext = new LdapTest().createDirContext("xxxx.net", 389, "cn=ldap,dc=xxxx,dc=net", "abcd@1234");
        System.out.println(dirContext);
    }

    public DirContext createDirContext(String ip, int port, String bindDn, String password){

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://" + ip + ":" + port);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, bindDn);
        env.put(Context.SECURITY_CREDENTIALS, password);
        DirContext ctx = null;

        try {
            ctx = new InitialDirContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }


        return ctx;
    }

    public void addItem(String ip, int port, String bindDN, String password, String itemDn,
                        HashMap<String, ArrayList<String>> attrValueMap)
            throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://" + ip + ":" + port);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, bindDN);
        env.put(Context.SECURITY_CREDENTIALS, password);
        DirContext ctx = null;
        try {
            ctx = new InitialDirContext(env);

            BasicAttributes entry = new BasicAttributes(true);

            Iterator<String> defaultAttrValueMapKeyIt = attrValueMap.keySet().iterator();
            while (defaultAttrValueMapKeyIt.hasNext()) {
                String attr = defaultAttrValueMapKeyIt.next();
                ArrayList<String> valueList = attrValueMap.get(attr);
                if (1 == valueList.size()) {
                    entry.put(attr, valueList.get(0));
                } else {
                    Attribute attribute = new BasicAttribute(attr);
                    for (String value : valueList) {
                        attribute.add(value);
                    }
                    entry.put(attribute);
                }
            }
            ctx.createSubcontext(itemDn, entry);
        } catch (NamingException e) {
            throw e;
        } finally {
            ctx.close();
        }
    }

}
