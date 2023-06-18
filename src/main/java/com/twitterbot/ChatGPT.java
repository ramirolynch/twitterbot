package com.twitterbot;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.twitterbot.model.TweetEntity;
import com.twitterbot.repositories.TweetRepository;

@Component
@Order(1)
public class ChatGPT implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger log = LoggerFactory.getLogger(ChatGPT.class);

	@Value("${app.twitter.open-ai}")
	private String apiKeys;

	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		log.info("ApplicationListener#onApplicationEvent()");

		OpenAiService service = new OpenAiService(apiKeys);

		CompletionRequest completionRequest = CompletionRequest.builder()
				.prompt("write a complete one liner joke in the style of Steven Wright").model("text-davinci-003")
				.maxTokens(1000).echo(false).build();

		//service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

		List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();

		for (CompletionChoice choice : choices) {
			ZonedDateTime createdTimestamp = ZonedDateTime.now();
			String post = choice.getText().trim().replaceAll("^\"|\"$", "");
			TweetEntity tweet = new TweetEntity(post, createdTimestamp);
			TweetEntity savedTweet = tweetRepository.save(tweet);
			System.out.println(savedTweet);
		}
	}
}
