package com.twitterbot.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.services.TweetService;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class SendTweetScheduler {

	private static final Logger log = LoggerFactory.getLogger(SendTweetScheduler.class);

	@Autowired
	private TweetService tweetService;

	@Scheduled(fixedDelayString = "PT8H", initialDelay = 10000L)
	public void postJoke() throws InterruptedException {
		tweetService.deleteOldestTweet();
		String post = tweetService.getOldestTweet();
		tweetService.sendTweet(post);
		
		log.info("Tweet posted.");
		Thread.sleep(10000L);
	}
}
