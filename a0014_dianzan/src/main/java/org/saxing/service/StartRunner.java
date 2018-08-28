package org.saxing.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.saxing.bean.ThumbRecord;
import org.saxing.dao.OrganizeDao;
import org.saxing.dao.ThumbDao;
import org.saxing.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by saxing on 17-11-1.
 */
@Component
@Order(value = 10)
@EnableScheduling
public class StartRunner implements CommandLineRunner {

    private final ThumbService thumbService;
    private final OrganizeService organizeService;

    @Autowired
    public StartRunner(ThumbService thumbService, OrganizeService organizeService) {
        this.thumbService = thumbService;
        this.organizeService = organizeService;
    }


    @Override
    public void run(String... strings) throws Exception {
        doRefreshToken();
        executeDepart();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void executeCron(){
        doRefreshToken();
    }

    private void doRefreshToken(){
        Runnable runnable = () -> {
            try {
                Thread.sleep(6000);
                System.out.println("...........................");
                System.out.println("...........................");
                System.out.println("...........................");
                System.out.println("...........................");
                System.out.println("...........................");
                WxUtils.del1Token();
                WxUtils.refreshToken();
                WxUtils.refreshToken();
                System.out.println("----------------------------------------");
                System.out.print("现在时间： " + LocalDateTime.now().toLocalDate() + "  \t");
                System.out.println(LocalDateTime.now().toLocalTime());
                System.out.println("----------------------------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 每5分钟
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void executeDepart(){
        LocalDate localDate = LocalDate.now();
        //统计
        countDepartResult(localDate);
        //如果今天是1号2号， 则统计上个月和这个月
        if (localDate.getDayOfMonth() < 3){
            localDate = localDate.minusMonths(1);
            countDepartResult(localDate);
        }
    }

    /**
     * 每天凌晨3點執行
     */
    @Scheduled(cron = "0 0 3 * * ? ")
    public void testExcute(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(organizeService::refreshAllUserId);
    }

    /**
     * 统计结果
     * @param localDate
     */
    private void countDepartResult(LocalDate localDate) {
        LocalDate date = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        String startDate = date.toString();
        String endDate = date.plusMonths(1).toString();
        List<ThumbRecord> results = thumbService.getThumb(startDate, endDate);
        if (CollectionUtils.isEmpty(results)){
            return;
        }
        Set<Integer> departIds = new HashSet<>();
        for (ThumbRecord t : results){
            departIds.add(t.getToDepartmentId());
        }
        Map<Integer, Object[]> goodMap = new HashMap<>();
        Map<Integer, Object[]> badMap = new HashMap<>();
        for (Integer i : departIds){
            goodMap.put(i, new Object[]{"未知", 0});
            badMap.put(i, new Object[]{"未知", 0});
        }

        for (ThumbRecord t : results){
            if (t.getThumbUp()>0){
                Object[] arr = goodMap.get(t.getToDepartmentId());
                arr[0] = t.getToDepartmentName();
                arr[1] = (Integer)arr[1] + 1;
                goodMap.put(t.getToDepartmentId(), arr);
            }else{
                Object[] arr = badMap.get(t.getToDepartmentId());
                arr[0] = t.getToDepartmentName();
                arr[1] = (Integer)arr[1] + 1;
                badMap.put(t.getToDepartmentId(), arr);
            }
        }

        int yearMonth = localDate.getYear()*100+localDate.getMonthValue();
        System.out.println("年月：" + yearMonth);
        if (MapUtils.isNotEmpty(goodMap)){
            //删除已经存在的结果，并插入新结果
            thumbService.delDepartCount(yearMonth);
            Map<String, Object> temp;
            for (Integer g : goodMap.keySet()){
                temp = new HashMap<>();
                temp.put("departId", g);
                String departName = goodMap.get(g)[0].toString();
                if ("未知".equals(departName)){
                    departName = badMap.get(g)[0].toString();
                }
                temp.put("departName", departName);
                temp.put("good", goodMap.get(g)[1]);
                temp.put("bad", badMap.get(g)[1]);
                temp.put("yearMonth", yearMonth);
                thumbService.saveDepartCount(temp);
            }
        }
    }


}
