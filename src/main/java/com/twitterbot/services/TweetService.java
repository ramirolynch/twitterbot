package com.twitterbot.services;

public interface TweetService {
	
	public void sendTweet(String post);
	
	public String getOldestTweet();

	public void deleteOldestTweet();

}
