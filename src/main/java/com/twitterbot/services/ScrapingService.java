package com.twitterbot.services;

import java.util.List;

public interface ScrapingService {
	
	List<String> scrapeHeadlines(String url);

}
