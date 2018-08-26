package org.saxing.qisi.service.runner;

import org.saxing.qisi.service.OrganizeService;
import org.saxing.qisi.service.WxTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 启动后运行
 *
 * Created by saxing on 2018/5/5.
 */
@Component
@Order(value = 10)
@EnableScheduling
public class StartRunner implements CommandLineRunner{

    private final WxTokenService wxTokenService;
    private final OrganizeService organizeService;

    @Autowired
    public StartRunner(WxTokenService wxTokenService, OrganizeService organizeService) {
        this.wxTokenService = wxTokenService;
        this.organizeService = organizeService;
    }

    @Override
    public void run(String... strings) throws Exception {
        doRefreshToken();
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
                wxTokenService.del1Token();
                wxTokenService.refreshToken();
                wxTokenService.refreshToken();
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
     * 每天凌晨3點執行
     */
    @Scheduled(cron = "0 0 3 * * ? ")
    public void refreshDepart(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(organizeService::refreshAllDepart);
    }
}
