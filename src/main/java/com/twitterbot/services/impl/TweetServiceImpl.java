package com.twitterbot.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;
import com.twitterbot.services.TweetService;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.signature.TwitterCredentials;

@Service
public class TweetServiceImpl implements TweetService {

	private static final Logger log = LoggerFactory.getLogger(TweetServiceImpl.class);

	@Value("${app.twitter.api-key}")
	private String apiKey;

	@Value("${app.twitter.api-key-secret}")
	private String apiKeySecret;

	@Value("${app.twitter.access-token}")
	private String accessToken;

	@Value("${app.twitter.access-token-secret}")
	private String accessTokenSecret;

	@Autowired
	TweetRepository tweetRepository;

	@Override
	public void sendTweet(String post) {
		try {
			TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder().accessToken(accessToken)
					.accessTokenSecret(accessTokenSecret).apiKey(apiKey).apiSecretKey(apiKeySecret).build());
			Tweet tweetSent = twitterClient.postTweet(post);
			System.out.println("Tweet id sent: " + tweetSent.getId());
		} catch (Exception e) {
			log.info("error :" + e);
		}
	}

	@Override
	public String getOldestTweet() {
		Optional<TweetEntity> oldestTweetOptional = tweetRepository.findFirstByOrderByIdAsc();
		return oldestTweetOptional.map(TweetEntity::getPost).orElse(null);
	}

	@Override
	public void deleteOldestTweet() {
		Optional<TweetEntity> oldestTweetOptional = tweetRepository.findFirstByOrderByIdAsc();
		if (!oldestTweetOptional.isEmpty()) {
			TweetEntity oldestTweetId = oldestTweetOptional.get();
			oldestTweetId.getId();
			tweetRepository.deleteById(oldestTweetId.getId());
		}

	}

}
