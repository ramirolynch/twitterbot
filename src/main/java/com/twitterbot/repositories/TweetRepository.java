package com.twitterbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitterbot.model.TweetEntity;



public interface TweetRepository extends JpaRepository<TweetEntity,Long>{

}
