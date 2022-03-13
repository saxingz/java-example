package org.saxing.a0048_activiti7;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * definition test
 *
 * @author saxing 2022/3/13 12:27
 */
@SpringBootTest
public class Part2_ProcessDefinition {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void getDefinitions() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list){
            System.out.println("-------新流程定义----------");
            System.out.println("getName: " + pd.getName());
            System.out.println("getKey: " + pd.getKey());
            System.out.println("getResourceName: " + pd.getResourceName());
            System.out.println("getDeploymentId: " + pd.getDeploymentId());
            System.out.println("getVersion: " + pd.getVersion());
        }
    }

    @Test
    public void delDefinition() {
        String pdId = "98dc0fc0-a1f9-11ec-805f-00ff2d5aa8a1";
        repositoryService.deleteDeployment(pdId, true);
        System.out.println("删除流程定义成功");
    }

}
