package com.twitterbot.scheduler;

import java.io.IOException;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.dto.ScrapeOnionDTO;
import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;
import com.twitterbot.services.ScrapingService;
import com.twitterbot.services.impl.ScrapeOnionEmailServiceImpl;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class ScrapeOnionScheduler {

	private static final Logger log = LoggerFactory.getLogger(ScrapeOnionScheduler.class);

	@Autowired
	ScrapingService scrapingService;

	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	ScrapeOnionEmailServiceImpl scrapeOnionEmailService;

	@Scheduled(cron = "0 54 3 * * 7")
	public int scrapeOnionHeadlines() throws InterruptedException {

		ScrapeOnionDTO scrapeOnionDTO = scrapingService.scrapeHeadlines("https://www.theonion.com/");

		int count = 0;

		for (String headline : scrapeOnionDTO.getHeadlines()) {

			ZonedDateTime createdTimestamp = ZonedDateTime.now();
			String post = headline + "\nvia @TheOnion";

			if (post.length() < 280) {
				TweetEntity tweet = new TweetEntity(post, createdTimestamp);
				TweetEntity saved = tweetRepository.save(tweet);
				if (saved != null) {
					count++;
				}
				log.info("From ScrapeOnionScheduler scrapeOnionHeadlines(): " + post);
			}
		}

		scrapeOnionDTO.setNumberOfHeadlines(count);

		try {
			scrapeOnionEmailService.sendSimpleMessage(null, null, scrapeOnionDTO);
		} catch (IOException e) {
			log.error("Error sending email at Scrape Onion Scheduler ", e);
		}
		return scrapeOnionDTO.getNumberOfHeadlines();
	}
}
