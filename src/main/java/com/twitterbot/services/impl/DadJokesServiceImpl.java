package com.twitterbot.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitterbot.dto.DadJokeDTO;
import com.twitterbot.services.DadJokesService;

@Service
public class DadJokesServiceImpl implements DadJokesService  {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public DadJokeDTO getRandomJoke() {
		WebClient client = WebClient.create();

		WebClient.ResponseSpec responseSpec = client.get()
			    .uri("https://dad-jokes.p.rapidapi.com/random/joke")
			    .header("X-RapidAPI-Key", "06b8cbe1f1mshc78c3e966686f05p1648b7jsnc8204dda4e82")
			    .header("X-RapidAPI-Host", "dad-jokes.p.rapidapi.com")
			    .retrieve();
		String response = responseSpec.bodyToMono(String.class).block();
		
		try {
	        DadJokeDTO dadJokeDTO = objectMapper.readValue(response, DadJokeDTO.class);
	        return dadJokeDTO;
	    } catch (JsonProcessingException e) {
	        // Handle the exception (e.g., log, throw a custom exception, return a default value)
	        e.printStackTrace();
	        // Return a default value or throw a custom exception
	        return null;
	    }
	}

}
