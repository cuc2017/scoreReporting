package com.cuc2017.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.cuc2017.model.Field;

@Service
public class TwitterServiceImpl implements TwitterService {

  private static final String WEB_SITE = "https://scores.cuc2019.com";

  private static final Logger log = LoggerFactory.getLogger(TwitterServiceImpl.class);

  private Map<String, TwitterTemplate> twitterTemplates;

  @Override
  @Async
  public void tweet(String tweetText) {
    try {
      TwitterTemplate twitterTemplate = getTwitterTemplates().get("General");
      if (twitterTemplate != null) {
        // TODO: comment for scoretraining
        twitterTemplate.timelineOperations().updateStatus(tweetText + "follow current games (" + WEB_SITE + ")");
        // TODO: uncomment for scoretraining
        log.info("Tweet to scores: " + tweetText);
      } else {
        log.info("Tweet to scores: " + tweetText);
      }
    } catch (RuntimeException e) {
      log.error("Unable to tweet " + tweetText, e);
    }
  }

  @Override
  @Async
  public void tweetToField(Field field, String tweetText) {
    try {
      // TwitterTemplate twitterTemplate =
      // getTwitterTemplates().get(field.getFieldName());
      // if (twitterTemplate != null) {
      // twitterTemplate.timelineOperations().updateStatus(tweetText +
      // CUC2017_SCORES_HASHTAG);
      // } else {
      log.info("Tweet to field: " + tweetText);
      // }
    } catch (RuntimeException e) {
      log.error("Unable to tweet to field " + field + ": " + tweetText, e);
    }
  }

  public Map<String, TwitterTemplate> getTwitterTemplates() {
    return twitterTemplates;
  }

  @Autowired
  public void setTwitterTemplates(Map<String, TwitterTemplate> twitterTemplates) {
    this.twitterTemplates = twitterTemplates;
  }

}
