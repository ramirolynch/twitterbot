package com.twitterbot.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.twitterbot.dto.ScrapeOnionDTO;
import com.twitterbot.services.ScrapeOnionEmailService;

@Component
public class ScrapeOnionEmailServiceImpl implements ScrapeOnionEmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(
      String to, String subject, ScrapeOnionDTO scrapeOnionDTO) throws IOException {
		

        // Load the template file from the resources
        ClassPathResource resource = new ClassPathResource("templates/scrapeonion.txt");
        InputStream inputStream = resource.getInputStream();
        
        // Read the contents of the template file
        String templateContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        inputStream.close();
        
        String text = String.format(templateContent, scrapeOnionDTO.getNumberOfHeadlines(),scrapeOnionDTO.getCreatedTimestamp(),scrapeOnionDTO.getHeadlines());
      
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@ramirolynch.com");
        message.setTo("ramirolynch@yahoo.com"); 
        message.setSubject("Scrape Onion Summary"); 
        message.setText(text);
        emailSender.send(message);
    }
}