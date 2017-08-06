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

    // String field5ConsumerKey = "";
    // String field5ConsumerSecret = "";
    // String field5AccessToken = "";
    // String field5AccessTokenSecret = "";

    String field19ConsumerKey = "EV4ydDJZDJpbTsiuVakcAr0B1";
    String field19ConsumerSecret = "pMMl8VM2ABIF5QulVn7YhLPSN2dJWMGmfxn9PWVdk4w6v9yufP";
    String field19AccessToken = "893805046160293888-MJWRPGSaJJOXq5DedhHFKQ2vwL7KUgN";
    String field19AccessTokenSecret = "FhQDVL5Qka7GaACeYCv6A9csE9rodMkqfYJoWUwOZLYy3";

    // String fieldMNPParkConsumerKey = "";
    // String fieldMNPParkConsumerSecret = "";
    // String fieldMNPParkAccessToken = "";
    // String fieldMNPParkAccessTokenSecret = "";

    // twitterTemplates.put("General",
    // new TwitterTemplate(consumerKey, consumerSecret, accessToken,
    // accessTokenSecret));
    // twitterTemplates.put("Field 1", new TwitterTemplate(field1ConsumerKey,
    // field1ConsumerSecret, field1AccessToken,
    // field1AccessTokenSecret));
    // twitterTemplates.put("Field 3", new TwitterTemplate(field3ConsumerKey,
    // field3ConsumerSecret, field3AccessToken,
    // field3AccessTokenSecret));
    // twitterTemplates.put("Field 4", new TwitterTemplate(field4ConsumerKey,
    // field4ConsumerSecret, field4AccessToken,
    // field4AccessTokenSecret));
    // twitterTemplates.put("Field 5",
    // new TwitterTemplate(field5ConsumerKey, field5ConsumerSecret,
    // field5AccessToken, field5AccessTokenSecret));
    // twitterTemplates.put("Field 6",
    // new TwitterTemplate(field6ConsumerKey, field6ConsumerSecret,
    // field6AccessToken, field6AccessTokenSecret));
    // twitterTemplates.put("Field 7",
    // new TwitterTemplate(field7ConsumerKey, field7ConsumerSecret,
    // field7AccessToken, field7AccessTokenSecret));
    // twitterTemplates.put("Field 8",
    // new TwitterTemplate(field8ConsumerKey, field8ConsumerSecret,
    // field8AccessToken, field8AccessTokenSecret));
    // twitterTemplates.put("Field 9",
    // new TwitterTemplate(field9ConsumerKey, field9ConsumerSecret,
    // field9AccessToken, field9AccessTokenSecret));
    // twitterTemplates.put("Field 10",
    // new TwitterTemplate(field10ConsumerKey, field10ConsumerSecret,
    // field10AccessToken, field10AccessTokenSecret));
    // twitterTemplates.put("Field 11",
    // new TwitterTemplate(field11ConsumerKey, field11ConsumerSecret,
    // field11AccessToken, field11AccessTokenSecret));
    // twitterTemplates.put("Field 12",
    // new TwitterTemplate(field12ConsumerKey, field12ConsumerSecret,
    // field12AccessToken, field12AccessTokenSecret));
    // twitterTemplates.put("Field 13",
    // new TwitterTemplate(field13ConsumerKey, field13ConsumerSecret,
    // field13AccessToken, field13AccessTokenSecret));
    // twitterTemplates.put("Field 14",
    // new TwitterTemplate(field14ConsumerKey, field14ConsumerSecret,
    // field14AccessToken, field14AccessTokenSecret));
    // twitterTemplates.put("Field 15",
    // new TwitterTemplate(field15ConsumerKey, field15ConsumerSecret,
    // field15AccessToken, field15AccessTokenSecret));
    // twitterTemplates.put("Field 16",
    // new TwitterTemplate(field16ConsumerKey, field16ConsumerSecret,
    // field16AccessToken, field16AccessTokenSecret));
    // twitterTemplates.put("Field 17",
    // new TwitterTemplate(field17ConsumerKey, field17ConsumerSecret,
    // field17AccessToken, field17AccessTokenSecret));
    // twitterTemplates.put("Field 18",
    // new TwitterTemplate(field18ConsumerKey, field18ConsumerSecret,
    // field18AccessToken, field18AccessTokenSecret));
    twitterTemplates.put("Field 19",
        new TwitterTemplate(field19ConsumerKey, field19ConsumerSecret, field19AccessToken, field19AccessTokenSecret));
    // twitterTemplates.put("MNP Park",
    // new TwitterTemplate(fieldMNPParkConsumerKey,
    // fieldMNPParkConsumerSecret, fieldMNPParkAccessToken,
    // fieldMNPParkAccessTokenSecret));
    return twitterTemplates;
  }
}
