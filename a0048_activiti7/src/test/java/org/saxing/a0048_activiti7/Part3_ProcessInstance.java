package org.saxing.a0048_activiti7;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * instance test
 *
 * @author saxing 2022/3/13 13:57
 */
@SpringBootTest
public class Part3_ProcessInstance {

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 初始化
     */
    @Test
    public void initProcessInstance() {
        // 1. 获取页面表单填报的内容， 请假时间，请假事由，String formData
        // 2. formData 写入业务表，返回业务表主键ID=businessKey
        // 3. 反业务数据与Activiti7流程数据关联
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Task", "bkey002");
        System.out.println("流程实例id: " + processInstance.getProcessDefinitionId());
    }

    /**
     * 获取
     */
    @Test
    public void getProcessInstances(){
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance pi : list) {
            System.out.println("------流程实例------");
            System.out.println("getProcessInstanceId: " + pi.getProcessInstanceId());
            System.out.println("getProcessDefinitionId: " + pi.getProcessDefinitionId());
            System.out.println("isEnded: " + pi.isEnded());
            System.out.println("isSuspended: " + pi.isSuspended());
        }
    }

    /**
     * 激活
     */
    @Test
    public void activeProcessInstances(){
//        runtimeService.suspendProcessInstanceById("93e367e2-a29a-11ec-be1e-00ff2d5aa8a1");
//        System.out.println("挂起流程实例");

        runtimeService.activateProcessInstanceById("93e367e2-a29a-11ec-be1e-00ff2d5aa8a1");
        System.out.println("激活流程实例");
    }

    /**
     * 删除
     */
    @Test
    public void delProcessInstances(){
        runtimeService.deleteProcessInstance("93e367e2-a29a-11ec-be1e-00ff2d5aa8a1", "删除玩");
        System.out.println("删除流程实例");
    }

}
