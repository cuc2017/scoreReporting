package com.cuc2017.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterTemplateCreator {

  // @Autowired
  // private Environment env;

  @Bean
  public TwitterTemplate twitterTemplate() {
    // String consumerKey = env.getProperty(accountName + ".consumerKey");
    // String consumerSecret = env.getProperty(accountName + ".consumerSecret");
    // String accessToken = env.getProperty(accountName + ".accessToken");
    // String accessTokenSecret = env.getProperty(accountName +
    // ".accessTokenSecret");
    String consumerKey = "eqRd6atEvKGWP7cdVaYMFFbZL";
    String consumerSecret = "Cm43Ye0Og8DRuSLa9kIi6t62uKcpPyIABqh9EWB1x3Irr5mhn1";
    String accessToken = "892704245589446656-vMcIbcTDU4f6fjxfOh5ns16mQyv2aia";
    String accessTokenSecret = "QcSi8JcwNCqBeupjkxEeENeRaLCtMNQ7HPMAcMHTQz9eQ";
    // Preconditions.checkNotNull(consumerKey);
    // Preconditions.checkNotNull(consumerSecret);
    // Preconditions.checkNotNull(consumerSecret);
    // Preconditions.checkNotNull(accessTokenSecret);

    TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    return twitterTemplate;
  }
}
