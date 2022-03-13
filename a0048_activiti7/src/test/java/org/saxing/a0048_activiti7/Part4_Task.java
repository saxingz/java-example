package org.saxing.a0048_activiti7;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * task test
 *
 * @author saxing 2022/3/13 15:22
 */
@SpringBootTest
public class Part4_Task {

    @Autowired
    private TaskService taskService;

    /**
     * 列表
     */
    @Test
    public void getTasks(){
        List<Task> list = taskService.createTaskQuery().list();
        for (Task tk : list) {
            System.out.println("getId: " + tk.getId());
            System.out.println("getName: " + tk.getName());
            System.out.println("getAssignee: " + tk.getAssignee());
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void getTaskByAssignee() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("1").list();
        for (Task tk : list) {
            System.out.println("getId: " + tk.getId());
            System.out.println("getName: " + tk.getName());
            System.out.println("getAssignee: " + tk.getAssignee());
        }
    }

    /**
     * 执行
     */
    @Test
    public void completeTask() {
        // 1e0d43ba-a2a2-11ec-89ce-00ff2d5aa8a1
//        taskService.complete("1e0d43ba-a2a2-11ec-89ce-00ff2d5aa8a1");
        // 7e798053-a2a8-11ec-990c-00ff2d5aa8a1
        taskService.complete("7e798053-a2a8-11ec-990c-00ff2d5aa8a1");
        System.out.println("完成任务");
    }

    /**
     * 拾取任务
     */
    @Test
    public void claimTask(){
//        List<Task> list = taskService.createTaskQuery().taskCandidateUser("1").list();

        taskService.claim("386dc226-a2aa-11ec-8edd-00ff2d5aa8a1", "1");
    }

    /**
     * 归还与交办任务
     */
    @Test
    public void setAssignTask() {
        Task task = taskService.createTaskQuery().taskId("386dc226-a2aa-11ec-8edd-00ff2d5aa8a1").singleResult();
        // 归还任务
        taskService.setAssignee("386dc226-a2aa-11ec-8edd-00ff2d5aa8a1", "null");
        // 交办任务
        taskService.setAssignee("386dc226-a2aa-11ec-8edd-00ff2d5aa8a1", "2");
    }

}
