package com.twitterbot.services;

import com.twitterbot.dto.ScrapeOnionDTO;

public interface ScrapingService {
	
	ScrapeOnionDTO scrapeHeadlines(String url);

}
