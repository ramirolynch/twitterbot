package com.twitterbot;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
//@EnableScheduling
@ComponentScan("com.twitterbot")
public class SchedulingConfig {

  //method in service class, annotated with fixed time and time unit
@Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
	public void tweet() {
	
	System.out.println("hello world");
	
	
}
}