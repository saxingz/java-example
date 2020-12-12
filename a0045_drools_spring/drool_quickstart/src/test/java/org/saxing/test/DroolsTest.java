package org.saxing.test;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.saxing.drools.entity.Order;
import org.saxing.drools.entity.Student;
import org.saxing.drools.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * test
 *
 * @author saxing 2020/12/11 23:31
 */
public class DroolsTest {

    @Test
    public void test1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();

        System.out.println(kieContainer.verify().getMessages().toString());


        KieSession session = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(50d);
        System.out.println("开始： " + order.getRealPrice());

        session.insert(order);
        session.fireAllRules();
        session.dispose();
        System.out.println("结束： " + order.getRealPrice());
    }

    @Test
    public void testNoloop() {
        Student student = new Student();
        student.setAge(50);

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.insert(student);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testAgendaGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.getAgenda().getAgendaGroup("agendagroup_2").setFocus();

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testTimer() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        new Thread(kieSession::fireUntilHalt).start();

        Thread.sleep(10000L);

        kieSession.halt();
        kieSession.dispose();
    }

    @Test
    public void testDateeffective() throws InterruptedException {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.fireAllRules();

        kieSession.dispose();
    }

    @Test
    public void testGlobal() throws InterruptedException {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.setGlobal("count", 5);

        List<String> list = new ArrayList<>();
        list.add("init");
        kieSession.setGlobal("gList", list);

        kieSession.setGlobal("userService", new UserService());

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testQuery() throws InterruptedException {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s1 = new Student();
        s1.setAge(50);
        kieSession.insert(s1);
        Student s2 = new Student();
        s2.setAge(80);
        s2.setName("lisi");
        kieSession.insert(s2);

        QueryResults results1 = kieSession.getQueryResults("query_1");
        int size = results1.size();
        System.out.println(size);

        for (QueryResultsRow row : results1) {
            Student s = (Student) row.get("$s");
            System.out.println(s);
        }

        QueryResults results2 = kieSession.getQueryResults("query_2", "lisi");
        int size2 = results2.size();
        System.out.println("query_2 result.size: " + size2);

        for (QueryResultsRow row : results2) {
            Student s = (Student) row.get("$s");
            System.out.println(s);
        }

        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("a_"));
        kieSession.dispose();
    }

    @Test
    public void testFunction() throws InterruptedException {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s1 = new Student();
        s1.setAge(50);
        kieSession.insert(s1);
        Student s2 = new Student();
        s2.setAge(80);
        s2.setName("lisi");
        kieSession.insert(s2);

//        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("b_"));
        // ^(d_|b_|c_).*$
        // ^(d_|b_|c_).*
        // (?=d_|b_|c_)[^']*
        // (d_|b_|c_)[^']*
        kieSession.fireAllRules(new RuleNameMatchesAgendaFilter("^(d_|b_|c_).*"));
//        kieSession.fireAllRules(new RuleNameMatchesAgendaFilter("(?=d_|b_|c_)[^']*"));
//        kieSession.fireAllRules(new RuleNameMatchesAgendaFilter("(d_|b_|c_)[^']*"));
        kieSession.dispose();
    }

}
