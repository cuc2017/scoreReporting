package com.cuc2017.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements TwitterService {

  private static final Logger log = LoggerFactory.getLogger(TwitterServiceImpl.class);

  private TwitterTemplate twitterTemplate;

  @Override
  @Async
  public void tweet(String tweetText) {
    try {
      getTwitterTemplate().timelineOperations().updateStatus(tweetText);
    } catch (RuntimeException e) {
      log.error("Unable to tweet " + tweetText, e);
    }
  }

  public TwitterTemplate getTwitterTemplate() {
    return twitterTemplate;
  }

  @Autowired
  public void setTwitterTemplate(TwitterTemplate twitterTemplate) {
    this.twitterTemplate = twitterTemplate;
  }

}
