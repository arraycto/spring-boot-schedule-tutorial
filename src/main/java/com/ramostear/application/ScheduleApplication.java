package com.ramostear.application;

import com.ramostear.application.schedule.DemoScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class ScheduleApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoScheduler.class);
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		logger.info("application start time ::" +sdf.format(new Date()));
		SpringApplication.run(ScheduleApplication.class, args);
	}

}
