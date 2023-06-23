package com.twitterbot.scheduler;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;

@Configuration
@EnableScheduling
@ComponentScan("com.twitterbot")
public class ChatGPTJokeScheduler {

	private static final Logger log = LoggerFactory.getLogger(ChatGPTJokeScheduler.class);

	@Value("${app.twitter.open-ai}")
	private String apiKeys;

	@Autowired
	private TweetRepository tweetRepository;

	String setup;
	String punchline;
	String authorName;

	@Scheduled(fixedDelayString = "PT7H", initialDelay = 10000L)
	public void saveJoke() throws InterruptedException {

		OpenAiService service = new OpenAiService(apiKeys);

		CompletionRequest completionRequest = CompletionRequest.builder()
				.prompt("write an original one-liner joke").model("text-davinci-003")
				.maxTokens(1000).echo(false).build();

		// service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

		List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();

		for (CompletionChoice choice : choices) {
			ZonedDateTime createdTimestamp = ZonedDateTime.now();
			String post = choice.getText().trim().replaceAll("^\"|\"$", "");
			TweetEntity tweet = new TweetEntity(post, createdTimestamp);
			TweetEntity savedTweet = tweetRepository.save(tweet);
			log.info("From ChatGPTJokeScheduler saveJoke(): " + savedTweet.toString());
		}
	}
}