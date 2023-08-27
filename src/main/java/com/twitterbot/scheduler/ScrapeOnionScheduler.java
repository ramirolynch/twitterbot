package com.twitterbot.scheduler;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;
import com.twitterbot.services.ScrapingService;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class ScrapeOnionScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(ScrapeOnionScheduler.class);
	
	@Autowired
	ScrapingService scrapingService;
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Scheduled(cron = "0 51 3 * * 6")
	public int scrapeOnionHeadlines() throws InterruptedException  {

		List<String> headlines = scrapingService.scrapeHeadlines("https://www.theonion.com/");

		for (String headline : headlines) {

			ZonedDateTime createdTimestamp = ZonedDateTime.now();
			String post = "BREAKING: " + headline + "\n written by: #TheOnion";

			if (post.length() < 280) {
				TweetEntity tweet = new TweetEntity(post, createdTimestamp);
				tweetRepository.save(tweet);
				log.info("From ScrapeOnionScheduler scrapeOnionHeadlines(): " + post);
				
			}
		}
		return headlines.size();
	}
}
