package com.twitterbot.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.twitterbot.services.ChatGptService;

@Service
public class ChatGptServiceImpl implements ChatGptService {
	private static final Logger log = LoggerFactory.getLogger(ChatGptServiceImpl.class);

	@Value("${app.twitter.open-ai}")
	private String apiKeys;
	
	@Override
	public String getJoke(String prompt) {

		log.info("ApplicationListener#onApplicationEvent()");

		OpenAiService service = new OpenAiService(apiKeys);

		CompletionRequest completionRequest = CompletionRequest.builder()
				.prompt(prompt).model("text-davinci-003")
				.maxTokens(1000).echo(false).build();

		// service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

		List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();

		String joke = null;

		for (CompletionChoice choice : choices) {
			joke = choice.getText();
			if (joke.length() > 0) {
				break;
			}
		}
		return joke;
	}
}
