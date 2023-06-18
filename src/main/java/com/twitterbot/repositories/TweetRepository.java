package com.twitterbot.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twitterbot.model.TweetEntity;

public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
	
	@Query("SELECT t.post FROM TweetEntity t ORDER BY t.createdTimestamp ASC")
	public List<String> getOldestTweet(Pageable pageable);

}
