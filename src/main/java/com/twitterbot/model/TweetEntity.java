package com.twitterbot.model;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tweets")
public class TweetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String post;
	ZonedDateTime createdTimestamp;

	public TweetEntity(String post, ZonedDateTime createdTimestamp) {
		this.post = post;
		this.createdTimestamp = createdTimestamp;
	}
	
	@Override
	public String toString(){
		return "Tweet posted: " + post + "at : " + createdTimestamp.toString();
	}
	

}
