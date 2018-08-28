package org.saxing.controller;

import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.saxing.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by saxing on 17-10-31.
 */
@Controller
@RequestMapping("/organize")
public class OrganizeController {

    private final OrganizeService organizeService;

    @Autowired
    public OrganizeController(OrganizeService organizeService) {
        this.organizeService = organizeService;
    }

    /**
     * 获取部门
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取部门， 0为全部部门", notes = "返回示例： {\"errcode\":0,\"errmsg\":\"ok\",\"department\":[{\"name\":\"迎宾大道\",\"id\":1,\"parentid\":0,\"order\":2147483447},{\"name\":\"运营部\",\"id\":2,\"parentid\":1,\"order\":2147483447},{\"name\":\"财务管理部\",\"id\":3,\"parentid\":1,\"order\":2147483247},{\"name\":\"运营子1\",\"id\":4,\"parentid\":2,\"order\":100000000}]}")
    @GetMapping("/getDepart/{id}")
    @ResponseBody
    public String getDepartment(@PathVariable("id") Integer id){
        JSONObject jsonObject = organizeService.downLoadDepartFromWx(id);
        return jsonObject.toString();
    }
    /**
     * 获取部门和员工
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID获取部门和员工， 0为全部部门", notes = "返回示例： {\"errcode\":0,\"userlist\":[{\"name\":\"孙超\",\"department\":[1,3,4],\"userid\":\"SunChao\"},{\"name\":\"刘罕\",\"department\":[1,3],\"userid\":\"LiuHan\"}],\"errmsg\":\"ok\",\"department\":[{\"name\":\"迎宾大道\",\"id\":1,\"parentid\":0,\"order\":2147483447},{\"name\":\"财务管理部\",\"id\":3,\"parentid\":1,\"order\":2147483247},{\"name\":\"运营部\",\"id\":2,\"parentid\":1,\"order\":2147483447},{\"name\":\"运营子1\",\"id\":4,\"parentid\":2,\"order\":100000000}]}")
    @GetMapping("/getDepartAndUser/{id}")
    @ResponseBody
    public String getDepartmentAndUser(@PathVariable("id") Integer id){
        JSONObject jsonObject = organizeService.getDepartAndUser(id);
        return jsonObject.toString();
    }

    /**
     * 获取部门用户
     * @param id
     * @return
     */
    @ApiOperation(value = "根据部门ID获取部门下员工", notes = "返回示例： {\"errcode\":0,\"userlist\":[{\"name\":\"孙超\",\"department\":[1,3,4],\"userid\":\"SunChao\"},{\"name\":\"刘罕\",\"department\":[1,3],\"userid\":\"LiuHan\"}],\"errmsg\":\"ok\"}")
    @GetMapping("/getDepartUser/{id}")
    @ResponseBody
    public String getDepartUser(@PathVariable("id") Integer id){
        JSONObject jsonObject = organizeService.downLoadDepartUserFromWx(id);
        return jsonObject.toString();
    }

    /**
     * 一般不用， 刷新數據庫人員
     * @return
     */
    @ApiOperation(value = "刷新數據庫人員", notes = "")
    @GetMapping("/refreshOrganizeUser")
    @ResponseBody
    public String refreshOrganizeUser(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(organizeService::refreshAllUserId);
        return "success";
    }


}
