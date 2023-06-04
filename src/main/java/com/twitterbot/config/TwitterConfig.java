package com.twitterbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfig {

	@Bean
	@ConfigurationProperties(prefix = "app.twitter")
	TwitterProps twitterProps() {
		TwitterProps twitterProps = new TwitterProps();
		return twitterProps;
	}

}
