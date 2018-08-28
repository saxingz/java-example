package org.saxing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.saxing.bean.JSONResult;
import org.saxing.config.WxProperties;
import org.saxing.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author saxing
 * @description
 * @time 2017/10/30 0030 15:15
 */
@Controller
@RequestMapping(value = "/thumb")
public class ThumbController {

    private final ThumbService thumbService;
    private final WxProperties wxProperties;


    @Autowired
    public ThumbController(ThumbService thumbService, WxProperties wxProperties) {
        this.thumbService = thumbService;
        this.wxProperties = wxProperties;
    }

    @GetMapping("/index5")
    public String getIndex5(Model model){
        model.addAttribute("myname", "LiuHan");
        model.addAttribute("nowDepart", "d5d");
        return "index";
    }


    /**
     * 操作
     * @param thumbInfo
     * @return
     */
    @ApiOperation(value = "点赞操作", notes = " 示例 {\n"
                                                + "\t\"fromUserId\":\"222\",\n"
                                                + "\t\"toDepartmentId\":\"202\",\n"
                                                + "\t\"toUserId\":\"321\",\n"
                                                + "\t\"thumb\":\"1\",\n"
                                                + "\t\"reason\":\"非常22222好\"\n"
                                                + "}")
    @PostMapping(value = "/doThumb")
    @ResponseBody
    public String doThumb(@RequestBody Map<String, Object> thumbInfo){
        String fromUserId = MapUtils.getString(thumbInfo, "fromUserId");
        int toDepartmentId = MapUtils.getIntValue(thumbInfo, "toDepartmentId");
        String toUserId = MapUtils.getString(thumbInfo, "toUserId");
        int thumb = MapUtils.getIntValue(thumbInfo, "thumb");
        String reason = MapUtils.getString(thumbInfo, "reason", "");
        return thumbService.doThumb(fromUserId, toDepartmentId, toUserId, thumb, reason);
    }


    /**
     * 返回月度报表
     * @param year
     * @param month
     * @return
     */
    @ApiOperation(value = "获取月度报表", notes = "示例： /thumb/getReport/2016/12")
    @GetMapping(value = "/getReport/{year}/{month}")
    @ResponseBody
    public String getReport(@PathVariable("year") int year, @PathVariable("month") int month){
        if (year < 2000 || year > 3000 || month < 1 || month > 12){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return thumbService.getReport(year, month);
    }

    /**
     * 返回月度支行报表
     * @param year
     * @param month
     * @return
     */
    @ApiOperation(value = "返回月度支行报表", notes = "示例： /thumb/getZhihReport/2016/12")
    @GetMapping(value = "/getZhihReport/{year}/{month}")
    @ResponseBody
    public String getZhihReport(@PathVariable("year") int year, @PathVariable("month") int month){
        if (year < 2000 || year > 3000 || month < 1 || month > 12){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return thumbService.getZhihReport(year, month);
    }

    /**
     * 返回部门年度报表
     * @param year
     * @return
     */
    @ApiOperation(value = "返回部门年度报表", notes = "示例： /thumb/getYearReport/2016")
    @GetMapping(value = "/getYearReport/{year}")
    @ResponseBody
    public String getYearDepartReport(@PathVariable("year") int year){
        if (year < 2000 || year > 3000 ){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return thumbService.getYearDepartReport(year);
    }

    /**
     * 返回支行年度报表
     * @param year
     * @return
     */
    @ApiOperation(value = "返回支行年度报表", notes = "示例： /thumb/getZhihYearReport/2016")
    @GetMapping(value = "/getZhihYearReport/{year}")
    @ResponseBody
    public String getZhihYearReport(@PathVariable("year") int year){
        if (year < 2000 || year > 3000 ){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return thumbService.getZhihYearDepartReport(year);
    }

    /**
     * 获取总榜
     * @return
     */
    @ApiOperation(value = "获取总榜", notes = "示例： /thumb/getTotalReport")
    @GetMapping(value = "/getTotalReport")
    @ResponseBody
    public String getTotalReport(){
        return thumbService.getTotalReport();
    }

    /**
     * 获取最新点赞10条
     * @return
     */
    @ApiOperation(value = "/获取最新点赞10条", notes = "{\"result\":[{\"date\":\"11-01 19:24\",\"from\":\"刘罕\",\"to\":\"运营部\"}],\"message\":\"ok\",\"status\":1}")
    @GetMapping(value = "/getLastGood10")
    @ResponseBody
    public String getLastGood10(){
        return thumbService.getLastGood10();
    }

    /**
     * 获取当月所有点赞
     *
     * @return
     */
    @ApiOperation(value = "/获取当月所有点赞", notes = "")
    @ResponseBody
    @GetMapping(value = "/getCurGood")
    @Deprecated
    public String getCurMonthGood(){
        return "";
    }

    /**
     * 获取指定部门指定月所有点赞详情
     *
     * @return
     */
    @ApiOperation(value = "/获取指定部门指定月所有点赞详情", notes = "")
    @ResponseBody
    @GetMapping(value = "/getGoodDetail/{departId}/{year}/{month}")
    public String getByDepartAndYearMonth(
            @PathVariable("departId") int departId,
            @PathVariable("year") int year,
            @PathVariable("month") int month){
        if (year < 2000 || year > 3000 || month < 1 || month > 12){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return thumbService.getGoodDetailByDepartAndMonth(1, year, month, departId);
    }

    /**
     * 年度部门员工统计排名
     * @param departId
     * @param year
     * @return
     */
    @ApiOperation(value = "/年度部门员工统计排名")
    @ResponseBody
    @GetMapping(value = "/getPersonRankInYear/{departId}/{year}")
    public String getPersonRankInYear(
            @PathVariable("departId") int departId,
            @PathVariable("year") int year){
        if (year < 2000 || year > 3000){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return JSONResult.fillResultString(1, "success", thumbService.getPersonRankInYearService(1, year, departId));
    }

    /**
     * 月度部门员工统计排名
     * @param departId
     * @param year
     * @return
     */
    @ApiOperation(value = "/月度部门员工统计排名")
    @ResponseBody
    @GetMapping(value = "/getPersonRankInYearMonth/{departId}/{year}/{month}")
    public String getPersonRankInYearMonth(
            @PathVariable("departId") int departId,
            @PathVariable("year") int year,
            @PathVariable("month") int month){
        if (year < 2000 || year > 3000 || month < 1 || month > 12){
            return JSONResult.fillResultString(-1, "wrong input", null);
        }
        return JSONResult.fillResultString(1, "success", thumbService.getPersonRankInYearMonthService(1, year, month, departId));
    }
}
