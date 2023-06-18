package com.twitterbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.repositories.TweetRepository;
import com.twitterbot.services.ChatGptService;
import com.twitterbot.services.TweetService;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class SchedulingConfig {

	private static final Logger log = LoggerFactory.getLogger(SchedulingConfig.class);

	@Autowired
	private TweetService tweetService;

	@Scheduled(fixedDelayString = "PT12H", initialDelay = 10000L)
	public void postJoke() throws InterruptedException {
		String post = tweetService.getOldestTweet();
		tweetService.sendTweet(post);
		String deletedTweet = tweetService.deleteOldestTweet();
		
		if (deletedTweet != null) {
		    log.info("Tweet posted and deleted: {}", deletedTweet);
		} else {
		    log.info("No tweet was deleted.");
		}
		Thread.sleep(10000L);
	}
}