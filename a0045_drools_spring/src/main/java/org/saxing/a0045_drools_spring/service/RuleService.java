package org.saxing.a0045_drools_spring.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * rule service
 *
 * @author saxing 2020/12/16 0:29
 */
@Service
public class RuleService {

    @Autowired
    private KieBase kieBase;

    public void rule() {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
