package org.saxing.test;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.saxing.drools.entity.Order;

import java.util.Collection;

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
        order.setOriginalPrice(500d);
        System.out.println("开始： " + order.getRealPrice());

        session.insert(order);
        Collection<RuleRuntimeEventListener> ruleRuntimeEventListeners = session.getRuleRuntimeEventListeners();
        System.out.println(ruleRuntimeEventListeners);
        session.fireAllRules();
        session.dispose();
        System.out.println("结束： " + order.getRealPrice());

    }

}
