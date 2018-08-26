package org.saxing.qisi.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计
 *
 * Created by saxing on 2018/5/8.
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * 获取部门/支行统计数据  排名
     *
     * @param yearMonth 年月
     * @param departMode depart  zhihang
     * @return
     */
    @ApiOperation(value = "获取部门/支行统计数据  排名", notes = "yearMonth为2017-10统计年月，2017统计年" +
            "  ?yearMonth=2017-10&departType=depart")
    @GetMapping("/getDepartData.action")
    public String getDepartData(@RequestParam("yearMonth") String yearMonth,
                                   @RequestParam("departMode") String departMode){
        if (StringUtils.isEmpty(yearMonth) || StringUtils.isEmpty(departMode)){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        return statisticService.getDepartData(yearMonth, departMode);
    }

    /**
     * 获取个人数据  排名
     *
     * @param yearMonth 年月
     * @param departId departId
     * @return
     */
    @ApiOperation(value = "获取部门个人统计数据  排名", notes = "yearMonth为2017-10统计年月，2017统计年" +
            "  ?yearMonth=2017-10&departId=2")
    @GetMapping("/getPersonData.action")
    public String getPersonData(@RequestParam("yearMonth") String yearMonth,
                                @RequestParam("departId") int departId){
        if (StringUtils.isEmpty(yearMonth) || departId < 0){
            return JsonResult.fillResultString(-1, "wrong params", null);
        }
        return statisticService.getPersonData(yearMonth, departId);
    }

}
