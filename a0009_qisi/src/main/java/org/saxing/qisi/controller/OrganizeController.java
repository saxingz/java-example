package org.saxing.qisi.controller;

import io.swagger.annotations.ApiOperation;
import org.saxing.qisi.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * organize controller
 *
 * Created by saxing on 2018/5/6.
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
     * 一般不用， 刷新數據庫部门
     * @return
     */
    @ApiOperation(value = "刷新數據庫人員， 一般不用", notes = "")
    @GetMapping("/refreshOrganizeDepart.action")
    @ResponseBody
    public String refreshOrganizeDepart(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(organizeService::refreshAllDepart);
        return "success";
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public String getDepart(@PathVariable (value = "id") Integer id){
        return organizeService.downLoadDepartFromWx(id).toString();
    }

}
