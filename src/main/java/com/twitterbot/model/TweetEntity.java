package com.twitterbot.model;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
	
	@Column(name = "post")
	String post;
	
	@Column(name = "created_timestamp", nullable = false)
	ZonedDateTime createdTimestamp;
	
    public TweetEntity() {
    }

	public TweetEntity(String post, ZonedDateTime createdTimestamp) {
		this.post = post;
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		if (post == null || post.isEmpty()) {
			return ""; // or return some default value if desired
		}
		return post;
	}

	public String getPost() {
		return this.post;
	}

	public Long getId() {
		return this.id;
	}
	
	@PrePersist
    public void prePersist() {
        createdTimestamp = ZonedDateTime.now(); // Set the current time in the appropriate time zone
    }

}
