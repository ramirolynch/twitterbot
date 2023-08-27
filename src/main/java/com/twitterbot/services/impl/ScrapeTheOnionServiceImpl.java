package com.twitterbot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitterbot.services.ScrapeTheOnionService;
import com.twitterbot.services.ScrapingService;

public class ScrapeTheOnionServiceImpl implements ScrapeTheOnionService {
	
	@Autowired
	ScrapingService scrapingService;

	@Override
	public int scrapeHeadlines(String url, int numberOfHeadlines) {
		
		List<String> headlines = scrapingService.scrapeHeadlines("https://www.theonion.com/");
		
		
		
		return 0;
	}

}
