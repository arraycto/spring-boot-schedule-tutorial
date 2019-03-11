package com.ramostear.application.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ramostear
 * @create-time 2019/3/11 0011-21:49
 * @modify by :
 * @since:
 */
@Component
public class DemoScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DemoScheduler.class);

    @Scheduled(cron = "0/15 * 22-23 * * ?")
    public void cronJobSchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        logger.info("Java cron job expression scheduler::"+sdf.format(now));
    }

   @Scheduled(fixedRate=5000)
    public void fixedRateSchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        logger.info("Java fixedRate scheduler::"+sdf.format(now));
    }

    @Scheduled(fixedDelay = 5000,initialDelay = 10000)
    public void fixedRateAndInitialDelaySchedule(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        logger.info("Java fixedRate and initialDelay scheduler::"+sdf.format(now));
    }

}
