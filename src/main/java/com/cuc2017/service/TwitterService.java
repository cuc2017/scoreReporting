package com.cuc2017.service;

import com.cuc2017.model.Field;

public interface TwitterService {
  public void tweet(String tweetText);

  public void tweetToField(Field field, String tweetText);
}
