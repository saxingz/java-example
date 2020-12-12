package org.saxing.test;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.saxing.drools.entity.Order;
import org.saxing.drools.entity.Student;

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

        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
