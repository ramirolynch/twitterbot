package com.twitterbot.scheduler;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.twitterbot.dto.DadJokeDTO;
import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;
import com.twitterbot.services.DadJokesService;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class DadJokeScheduler {

	private static final Logger log = LoggerFactory.getLogger(DadJokeScheduler.class);
	
	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private DadJokesService dadJokesService;
	
	String setup;
	String punchline;
	String authorName;

	@Scheduled(fixedDelayString = "PT5H", initialDelay = 10000L)
	public void saveJoke() throws InterruptedException {

		DadJokeDTO dadJoke = dadJokesService.getRandomJoke();

		DadJokeDTO.Joke[] jokes = dadJoke.getBody(); // Access the array of jokes

		for (DadJokeDTO.Joke joke : jokes) {
			setup = joke.getSetup(); // Access the "setup" field
			punchline = joke.getPunchline(); // Access the "punchline" field
			authorName = joke.getAuthor().getName();
		}
		
		ZonedDateTime createdTimestamp = ZonedDateTime.now();
		String post = setup + "\n" + punchline + "\n" + "written by: " + authorName + " #joke";
		TweetEntity tweet = new TweetEntity(post, createdTimestamp);
		TweetEntity savedTweet = tweetRepository.save(tweet);
		
		log.info("From DadJokeScheduler saveJoke(): " + savedTweet.toString());
	}
}