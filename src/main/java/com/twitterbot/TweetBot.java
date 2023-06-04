package com.twitterbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.signature.TwitterCredentials;

@Component
@Order(1)
public class TweetBot implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger log = LoggerFactory.getLogger(TweetBot.class);

	@Value("${app.twitter.api-key}")
	private String apiKey;

	@Value("${app.twitter.api-key-secret}")
	private String apiKeySecret;

	@Value("${app.twitter.access-token}")
	private String accessToken;

	@Value("${app.twitter.access-token-secret}")
	private String accessTokenSecret;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		log.info("ApplicationListener#onApplicationEvent()");

		try {
			TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()

					.accessToken(accessToken).accessTokenSecret(accessTokenSecret).apiKey(apiKey).apiSecretKey(apiKeySecret)
					.build());

			Tweet tweetSent = twitterClient.postTweet("tweet with credentials in the application properties");

			System.out.println("Tweet id sent: " + tweetSent.getId());

		} catch (Exception e) {
			log.info("error :" + e);
		}

	}

}