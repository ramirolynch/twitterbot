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
	@Scheduled(fixedRate = 4 * 60 * 60 * 1000)
	public void startBot() {
		String joke = chatGptService.getJoke("write a short joke");
		tweetService.sendTweet(joke);
		System.out.println("Joke sent");
	}
}