package org.saxing.a0048_activiti7;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * uel test
 *
 * @author saxing 2022/3/13 23:32
 */
@SpringBootTest
public class Part6_UEL {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 启动流程实例带参数，执行执行人
     */
    @Test
    public void initProcessInstanceWithArgs() {
        // 流程变量
        // ${ZhiXingRen}
        Map<String, Object> variables = new HashMap<>();
        variables.put("ZhiXingRen", "2");

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("myProcess_UEL_V1", "bkey003", variables);
        System.out.println("流程实例id: " + processInstance.getProcessDefinitionId());
    }

    /**
     * 启动流程实例带参数
     */
    @Test
    public void completeTaskWithArgs() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("pay", "101");
        taskService.complete("19398e55-a2e7-11ec-998c-00ff2d5aa8a1", variables);
        System.out.println("完成任务");
    }

    /**
     * 启动流程实例带参数，使用实体类
     */
    @Test
    public void initProcessInstanceWithClassArgs() {

    }

    /**
     * 启动实例流程实例带参数，指定多个候选人
     */
    @Test
    public void initProcessInstanceWithCandidateArgs() {

    }

    /**
     * 直接指定流程变量
     */
    @Test
    public void otherArgs() {

    }
    /**
     * 局部变量
     */
    @Test
    public void otherLocalArgs() {

    }


}
