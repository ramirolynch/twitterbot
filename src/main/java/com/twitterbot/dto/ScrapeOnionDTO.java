package com.twitterbot.dto;

import java.time.Instant;
import java.util.List;

public class ScrapeOnionDTO {
	
	int numberOfHeadlines;
	List<String> headlines;
	Instant createdTimestamp;

	public ScrapeOnionDTO(int size, List<String> headlines2, Instant now) {
		this.numberOfHeadlines = size;
		this.headlines = headlines2;
		this.createdTimestamp = now;
	}
	
	public int getNumberOfHeadlines() {
		return numberOfHeadlines;
	}
	public void setNumberOfHeadlines(int numberOfHeadlines) {
		this.numberOfHeadlines = numberOfHeadlines;
	}
	public List<String> getHeadlines(){
		return headlines;
	}
	public void setHeadlines(List<String> headlines) {
		this.headlines = headlines;
	}
	public Instant getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Instant createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
}
