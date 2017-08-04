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
    Map<String, TwitterTemplate> twitterTemplates = new HashMap<>(20);
    String consumerKey = "VpY9CCxcniGMXIfPWnieCj1Tz";
    String consumerSecret = "zFEbbknWd5kUe7LkDPUfebnNJ3H6sQ9yMj47UILS3ctQnWPDgR";
    String accessToken = "893494170043723776-5frMtf5U5bT1rOBQ8OEMmFVBy3ahJEr";
    String accessTokenSecret = "78XsDvCduzSZGxakY9qXv9eyrtVHLwEFMda4mjxhFRUzH";

    // TEST account
    // String consumerKey = "eqRd6atEvKGWP7cdVaYMFFbZL";
    // String consumerSecret =
    // "Cm43Ye0Og8DRuSLa9kIi6t62uKcpPyIABqh9EWB1x3Irr5mhn1";
    // String accessToken =
    // "892704245589446656-vMcIbcTDU4f6fjxfOh5ns16mQyv2aia";
    // String accessTokenSecret =
    // "QcSi8JcwNCqBeupjkxEeENeRaLCtMNQ7HPMAcMHTQz9eQ";

    String field1ConsumerKey = "oCv7L1BhUW0jxu92VT74DHEsq";
    String field1ConsumerSecret = "CPUSruaXfOluUSxVaqDYlMwDyzulyUw7643A8RSd02K4Sv3CTG";
    String field1AccessToken = "893149778883878912-WXl912WYCYqIvFpM5nkaC14VX5xrmsf";
    String field1AccessTokenSecret = "YmiM9EfPTgXbCJjZHkvJDKJj1YPRr2A6wEMZO2pJ9PJjg";

    String field3ConsumerKey = "3o7jMWc3ax6A7KwcCwDkSAgwN";
    String field3ConsumerSecret = "lUDB4uhR5CxufPZpnp3UrU1srv9ce5aepMCqyMngMDJPR0BWjw";
    String field3AccessToken = "893417391757066240-khMgYitfrETRr8aqHCPvWPiEvgtZuOj";
    String field3AccessTokenSecret = "cOCuCoMxKRgY6XloChAmnaXeUPKSEGrsyi7fTOY1aZKwB";

    String field4ConsumerKey = "x7HTWcMggFu1RJbk7Gpbp2p1E";
    String field4ConsumerSecret = "qPfr4f8rc2u2BCLjMPfn6zsYGfMhAHlC8iCsADzvAxqQhoG1fc";
    String field4AccessToken = "893490482386542592-eHsmo10zhUTfG6nyBymP8IoO3s7jgA5";
    String field4AccessTokenSecret = "4BkuxFV7QhISgBVFZZ2o9uYwZ0l41KCQKVP1QEaPI6gva";

    twitterTemplates.put("General", new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret));
    twitterTemplates.put("Field 1",
        new TwitterTemplate(field1ConsumerKey, field1ConsumerSecret, field1AccessToken, field1AccessTokenSecret));
    twitterTemplates.put("Field 3",
        new TwitterTemplate(field3ConsumerKey, field3ConsumerSecret, field3AccessToken, field3AccessTokenSecret));
    twitterTemplates.put("Field 4",
        new TwitterTemplate(field4ConsumerKey, field4ConsumerSecret, field4AccessToken, field4AccessTokenSecret));
    return twitterTemplates;
  }
}
