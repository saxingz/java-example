package org.saxing.a0048_activiti7;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 *
 * @author saxing 2022/3/12 19:27
 *
 */
@SpringBootTest
class Part1_Deployment {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 发布 bpmn
     */
    @Test
    public void initDeploymentBPMN() {
        String filename = "BPMN/myProcess_Part1.bpmn";
        String pngName = "BPMN/myProcess_Part1.png";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(filename)
                .addClasspathResource(pngName)
                .name("流程部署测试BPMN").deploy();
        System.out.println(deployment.getName());
    }

    /**
     * 从zip中deploy bpmn
     */
    @Test
    public void initDeploymentZIP() {
        InputStream fileInputStream = this.getClass().getClassLoader()
                .getResourceAsStream("BPMN/myProcess_Part1-v2.zip");
        ZipInputStream zip = new ZipInputStream(fileInputStream);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zip)
                .name("流程部署测试zip")
                .deploy();
        System.out.println(deployment.getName());
    }

    /**
     * show all
     */
    @Test
    public void getDeployments() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment dep : list) {
            System.out.println("Id: " + dep.getId());
            System.out.println("Name: " + dep.getName());
            System.out.println("DeploymentTime: " + dep.getDeploymentTime());
            System.out.println("Key: " + dep.getKey());
        }
    }

}
