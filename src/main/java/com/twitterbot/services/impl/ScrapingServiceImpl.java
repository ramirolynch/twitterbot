package com.twitterbot.services.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twitterbot.dto.ScrapeOnionDTO;
import com.twitterbot.services.ScrapingService;

@Service
public class ScrapingServiceImpl implements ScrapingService {

	private static final Logger log = LoggerFactory.getLogger(ScrapingServiceImpl.class);

	@Override
	public ScrapeOnionDTO scrapeHeadlines(String url) {
		Document doc = null;
		List<String> headlines = new ArrayList<String>();
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			log.error("Error in ScrapingServiceImpl.scrapeHeadlines", e);
		}
		Elements links = doc.select("h4");
		for (Element link : links) {
			headlines.add(link.text());
			log.info("this is link text: " + link.text());
		}
	
		return new ScrapeOnionDTO(headlines.size(),headlines,Instant.now());
	}

}
