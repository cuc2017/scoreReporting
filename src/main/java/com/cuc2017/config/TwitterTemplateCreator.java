package com.cuc2017.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterTemplateCreator {

  @Bean
  public Map<String, TwitterTemplate> twitterTemplates() {
    Map<String, TwitterTemplate> twitterTemplates = new HashMap<>(18);
    String consumerKey = "eqRd6atEvKGWP7cdVaYMFFbZL";
    String consumerSecret = "Cm43Ye0Og8DRuSLa9kIi6t62uKcpPyIABqh9EWB1x3Irr5mhn1";
    String accessToken = "892704245589446656-vMcIbcTDU4f6fjxfOh5ns16mQyv2aia";
    String accessTokenSecret = "QcSi8JcwNCqBeupjkxEeENeRaLCtMNQ7HPMAcMHTQz9eQ";

    String field1ConsumerKey = "oCv7L1BhUW0jxu92VT74DHEsq";
    String field1ConsumerSecret = "CPUSruaXfOluUSxVaqDYlMwDyzulyUw7643A8RSd02K4Sv3CTG";
    String field1AccessToken = "893149778883878912-WXl912WYCYqIvFpM5nkaC14VX5xrmsf";
    String field1AccessTokenSecret = "YmiM9EfPTgXbCJjZHkvJDKJj1YPRr2A6wEMZO2pJ9PJjg";

    twitterTemplates.put("General", new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret));
    twitterTemplates.put("Field 1",
        new TwitterTemplate(field1ConsumerKey, field1ConsumerSecret, field1AccessToken, field1AccessTokenSecret));
    return twitterTemplates;
  }
}
