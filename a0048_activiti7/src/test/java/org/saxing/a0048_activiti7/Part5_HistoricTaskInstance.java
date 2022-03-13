package org.saxing.a0048_activiti7;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * history test
 *
 * @author saxing 2022/3/13 23:10
 */
@SpringBootTest
public class Part5_HistoricTaskInstance {

    @Autowired
    private HistoryService historyService;

    @Test
    public void historicTaskInstanceByUser() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc()
                .taskAssignee("1")
                .list();
        for (HistoricTaskInstance li : list) {
            System.out.println("getId: " + li.getId());
            System.out.println("getProcessInstanceId: " + li.getProcessInstanceId());
            System.out.println("getName: " + li.getName());
        }
        //getId: 93e69c36-a29a-11ec-be1e-00ff2d5aa8a1
        //getProcessInstanceId: 93e367e2-a29a-11ec-be1e-00ff2d5aa8a1
        //getName: null
        //getId: 1e0d43ba-a2a2-11ec-89ce-00ff2d5aa8a1
        //getProcessInstanceId: 1e094c16-a2a2-11ec-89ce-00ff2d5aa8a1
        //getName: 发起报销
    }

    @Test
    public void historicTaskInstanceByPid() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc()
                .processInstanceId("93e367e2-a29a-11ec-be1e-00ff2d5aa8a1")
                .list();
        for (HistoricTaskInstance li : list) {
            System.out.println("getId: " + li.getId());
            System.out.println("getProcessInstanceId: " + li.getProcessInstanceId());
            System.out.println("getName: " + li.getName());
        }
    }

}
