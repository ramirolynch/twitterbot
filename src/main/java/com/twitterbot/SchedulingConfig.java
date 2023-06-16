package com.twitterbot;

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
	@Scheduled(cron = "0 0  7,8 * *", zone = "CDT")
	public void startBot() {
		String joke = chatGptService.getJoke("write something motivational");
		tweetService.sendTweet(joke);
		System.out.println("Joke sent");
	}
}