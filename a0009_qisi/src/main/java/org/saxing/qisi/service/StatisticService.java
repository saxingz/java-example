package org.saxing.qisi.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.saxing.qisi.bean.JsonResult;
import org.saxing.qisi.dao.mapper.StatisticMapper;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StatisticService
 *
 * Created by saxing on 2018/5/9.
 */
@Service
public class StatisticService {

    private final StatisticMapper statisticMapper;

    @Autowired
    public StatisticService(StatisticMapper statisticMapper) {
        this.statisticMapper = statisticMapper;
    }

    /**
     * getDepartData
     *
     * @param yearMonth
     * @param departMode
     * @return
     */
    public String getDepartData(String yearMonth, String departMode) {
        //校验参数
        if (!departMode.equals(StringConstant.MODE_DEPART) && !departMode.equals(StringConstant.MODE_ZHIHANG)){
            return JsonResult.fillResultString(-1, "wrong type departType", null);
        }
        LocalDateTime[] seTime = this.getStartEndTime(yearMonth);
        if (seTime[0] == null){
            JsonResult.fillResultString(-1, "wrong yearMonth", null);
        }
        //get from sql
        List<Map<String, Object>> rank = statisticMapper.queryDepartData(seTime[0], seTime[1]);

        List<Map<String, Object>> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(rank)){
            if (departMode.equals(StringConstant.MODE_ZHIHANG)){
                for (Map<String, Object> data : rank){
                    if (MapUtils.getString(data, StringConstant.DEPART_NAME, "").contains(StringConstant.ZHIHANG)){
                        result.add(data);
                    }
                }
            }else {
                for (Map<String, Object> data : rank){
                    if (!MapUtils.getString(data, StringConstant.DEPART_NAME, "").contains(StringConstant.ZHIHANG)){
                        result.add(data);
                    }
                }
            }
        }

        return JsonResult.fillResultString(200, "success", result);
    }

    public String getPersonData(String yearMonth, int departId) {
        LocalDateTime[] seTime = this.getStartEndTime(yearMonth);
        if (seTime[0] == null){
            JsonResult.fillResultString(-1, "wrong yearMonth", null);
        }

        //get from sql
        List<Map<String, Object>> rank = statisticMapper.queryPersonData(departId, seTime[0], seTime[1]);
        return JsonResult.fillResultString(200, "success", rank);
    }

    /**
     * getStartEndTime
     *
     * @param yearMonth
     * @return LocalDateTime[startTime, endTime]
     */
    private LocalDateTime[] getStartEndTime(String yearMonth){
        LocalDateTime[] result = new LocalDateTime[2];

        //判断年月类型，分辨yearMonth , year
        String ymPattern = "\\d{4}-\\d{1,2}";
        String yPattern = "\\d{4}";
        Pattern ymP = Pattern.compile(ymPattern);
        Pattern yP = Pattern.compile(yPattern);

        int year, month;
        LocalDateTime startTime, endTime;
        Matcher ymM = ymP.matcher(yearMonth);
        Matcher yM = yP.matcher(yearMonth);
        if (ymM.matches()){
            //year
            year = Integer.parseInt(yearMonth.substring(0, yearMonth.indexOf("-")));
            month = Integer.parseInt(yearMonth.substring(yearMonth.indexOf("-") + 1, yearMonth.length()));
            startTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
            endTime = startTime.plusMonths(1).minusSeconds(1);
            result[0] = startTime;
            result[1] = endTime;
        }else if (yM.matches()){
            //yearMonth
            year = Integer.parseInt(yearMonth);
            startTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            endTime = startTime.plusYears(1).minusSeconds(1);
            result[0] = startTime;
            result[1] = endTime;
        }else {
            result[0] = null;
        }
        return result;
    }
}

























