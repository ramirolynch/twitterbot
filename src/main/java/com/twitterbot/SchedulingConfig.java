package com.twitterbot;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.services.ChatGptService;
import com.twitterbot.services.TweetService;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class SchedulingConfig {

	@Autowired
	private TweetService tweetService;

	@Autowired
	private ChatGptService chatGptService;

	// method in service class, annotated with fixed time and time unit
	@Scheduled(cron = "0 10,15 7,8 * * *", zone = "CDT")
	@Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
	public void startBot() {
		String joke = chatGptService.getJoke("write something motivational about Pittsburgh Steelers QB Mitch Trubisky");
		tweetService.sendTweet(joke);
		System.out.println("Joke sent");
	}
}