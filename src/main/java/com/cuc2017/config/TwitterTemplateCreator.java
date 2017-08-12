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
    // TEST account
    String consumerKey = "eqRd6atEvKGWP7cdVaYMFFbZL";
    String consumerSecret = "Cm43Ye0Og8DRuSLa9kIi6t62uKcpPyIABqh9EWB1x3Irr5mhn1";
    String accessToken = "892704245589446656-vMcIbcTDU4f6fjxfOh5ns16mQyv2aia";
    String accessTokenSecret = "QcSi8JcwNCqBeupjkxEeENeRaLCtMNQ7HPMAcMHTQz9eQ";

    // Test Account
    String field1ConsumerKey = "ZACOcwUx5B36a6j5QF9D02twP";
    String field1ConsumerSecret = "GxMCXuVUlM2fCyPjOUNhpxp6rQCCzRhkLfeBZBxkBxX3BH0xUG";
    String field1AccessToken = "894551712559759361-gVOytRo53TJxWTp14ehUS7g1glwmAa0";
    String field1AccessTokenSecret = "sWoTNDoXEb54x9GcOIfSdb69DU2jMLP2AM1EVcWjjcAVA";

    // String consumerKey = "VpY9CCxcniGMXIfPWnieCj1Tz";
    // String consumerSecret =
    // "zFEbbknWd5kUe7LkDPUfebnNJ3H6sQ9yMj47UILS3ctQnWPDgR";
    // String accessToken =
    // "893494170043723776-5frMtf5U5bT1rOBQ8OEMmFVBy3ahJEr";
    // String accessTokenSecret =
    // "78XsDvCduzSZGxakY9qXv9eyrtVHLwEFMda4mjxhFRUzH";

    // String field1ConsumerKey = "oCv7L1BhUW0jxu92VT74DHEsq";
    // String field1ConsumerSecret =
    // "CPUSruaXfOluUSxVaqDYlMwDyzulyUw7643A8RSd02K4Sv3CTG";
    // String field1AccessToken =
    // "893149778883878912-WXl912WYCYqIvFpM5nkaC14VX5xrmsf";
    // String field1AccessTokenSecret =
    // "YmiM9EfPTgXbCJjZHkvJDKJj1YPRr2A6wEMZO2pJ9PJjg";
    //
    // String field3ConsumerKey = "3o7jMWc3ax6A7KwcCwDkSAgwN";
    // String field3ConsumerSecret =
    // "lUDB4uhR5CxufPZpnp3UrU1srv9ce5aepMCqyMngMDJPR0BWjw";
    // String field3AccessToken =
    // "893417391757066240-khMgYitfrETRr8aqHCPvWPiEvgtZuOj";
    // String field3AccessTokenSecret =
    // "cOCuCoMxKRgY6XloChAmnaXeUPKSEGrsyi7fTOY1aZKwB";
    //
    // String field4ConsumerKey = "x7HTWcMggFu1RJbk7Gpbp2p1E";
    // String field4ConsumerSecret =
    // "qPfr4f8rc2u2BCLjMPfn6zsYGfMhAHlC8iCsADzvAxqQhoG1fc";
    // String field4AccessToken =
    // "893490482386542592-eHsmo10zhUTfG6nyBymP8IoO3s7jgA5";
    // String field4AccessTokenSecret =
    // "4BkuxFV7QhISgBVFZZ2o9uYwZ0l41KCQKVP1QEaPI6gva";

    // String field5ConsumerKey = "YiIhdlUoEVS75fZEXbZRmtYUT";
    // String field5ConsumerSecret =
    // "3beAPLIrLt9DVIWFZSuG8pZgNvxgOnc1Tr0hs5PCHR1hrLmZkI";
    // String field5AccessToken =
    // "895235908911452161-pCKeUbvxJ6EWqchXTgVszlIWj0dAs9V";
    // String field5AccessTokenSecret =
    // "nSF3evUTp3tX96GdkFvFLoO4308z8nVv7AEeFEtBmfGYE";

    // String field6ConsumerKey = "dcZXqXgyKFhtIebfesUbqFCPj";
    // String field6ConsumerSecret =
    // "cSo21tuKRGZa9wKCZfPQW4XQqjBSMMbwR5Vkcw8HlTN1zu5mn1";
    // String field6AccessToken =
    // "895590554066493440-EYTPUpYOMCizmogPE4wsWni2Vmrozl5";
    // String field6AccessTokenSecret =
    // "QTzr48KQLeETgeCeBIHp6atenonlBLNTdnxMhVsMiHWzq";
    //
    // String field7ConsumerKey = "nHxPCx0mSrxLrkpV5Q6TKiUHj";
    // String field7ConsumerSecret =
    // "mK8ulOzM6sbrZipYRTcfOQkV3rHrDLVgZXpfqSOxHHo3D0xVSf";
    // String field7AccessToken =
    // "895820569064726529-2exjCmjwuYDp29dMSfPnmI3fAi5j7MA";
    // String field7AccessTokenSecret =
    // "AtGiswzCZGgf9yUbiQP7U8rtgh6WXYlh6RFLgtdCX4d2b";
    //
    // String field8ConsumerKey = "K8ioMTyib0OQWifgZyOssVz5O";
    // String field8ConsumerSecret =
    // "HewL7ZHRAv4AFtfTYAKXB9iojqneLINjSCsDxrk2geZrLg2FQe";
    // String field8AccessToken =
    // "895825606449008643-1VkvKKEFhaPfhvlA9Oi31GmRdBCp5BU";
    // String field8AccessTokenSecret =
    // "qWJ2i8gW560ow8U13HuM1R9HywmvCZ2f1LkQ0fjzW91hC";

    // String field9ConsumerKey = "";
    // String field9ConsumerSecret = "";
    // String field9AccessToken = "";
    // String field9AccessTokenSecret = "";

    // String field10ConsumerKey = "";
    // String field10ConsumerSecret = "";
    // String field10AccessToken = "";
    // String field10AccessTokenSecret = "";

    // String field11ConsumerKey = "";
    // String field11ConsumerSecret = "";
    // String field11AccessToken = "";
    // String field11AccessTokenSecret = "";

    // String field12ConsumerKey = "";
    // String field12ConsumerSecret = "";
    // String field12AccessToken = "";
    // String field12AccessTokenSecret = "";

    // String field13ConsumerKey = "";
    // String field13ConsumerSecret = "";
    // String field13AccessToken = "";
    // String field13AccessTokenSecret = "";

    String field14ConsumerKey = "rfyuC7fSTS8rBa9g1nVeWgBAv";
    String field14ConsumerSecret = "dC8zi5aJWHfHqcAqOe9dSE7n7GZWz8EUPy8L39FSXUMMrk8vAD";
    String field14AccessToken = "896015576031780868-8jWP5bJzxdNYZaMZwAhZeqNcGHrUdsv";
    String field14AccessTokenSecret = "TC8SxqUn2vG81GrXT13eO60G0YU9ubvv6HMchH0EGiNSd";

    String field15ConsumerKey = "NOQL1p0Lo0lO9zJKKON9kDgFL";
    String field15ConsumerSecret = "uTu2vjoKen9WZgk0VkHCRjAu8039CVwl6KiIu3owzSu6JmoRtU";
    String field15AccessToken = "896079212964085761-kFVyroc8txi67uy8aKzGNC0hGSJISH6";
    String field15AccessTokenSecret = "quIYX6uQwPEXDlX3Q4qQ1Xc8jeU0Vfl1FRNUfAE2D6Os9";

    String field16ConsumerKey = "2fbr91GgSV5lp2qmCNMzjwa7C";
    String field16ConsumerSecret = "r2mLWfrkpwn22FUTFF9FXZIz8b2OxZLQkm34rk5nI6wbIxO2Cn";
    String field16AccessToken = "896085062193696768-Y7SoaYRukrAcRLg23l3uqLyxqOIWKv3";
    String field16AccessTokenSecret = "uHzHHh1oRyX6INNxST0kPRQegn1CgzLxK7a3WL1KwE0l2";

    // String field17ConsumerKey = "";
    // String field17ConsumerSecret = "";
    // String field17AccessToken = "";
    // String field17AccessTokenSecret = "";

    // String field18ConsumerKey = "";
    // String field18ConsumerSecret = "";
    // String field18AccessToken = "";
    // String field18AccessTokenSecret = "";

    // String field19ConsumerKey = "EV4ydDJZDJpbTsiuVakcAr0B1";
    // String field19ConsumerSecret =
    // "pMMl8VM2ABIF5QulVn7YhLPSN2dJWMGmfxn9PWVdk4w6v9yufP";
    // String field19AccessToken =
    // "893805046160293888-MJWRPGSaJJOXq5DedhHFKQ2vwL7KUgN";
    // String field19AccessTokenSecret =
    // "FhQDVL5Qka7GaACeYCv6A9csE9rodMkqfYJoWUwOZLYy3";

    // String fieldMNPParkConsumerKey = "";
    // String fieldMNPParkConsumerSecret = "";
    // String fieldMNPParkAccessToken = "";
    // String fieldMNPParkAccessTokenSecret = "";

    // twitterTemplates.put("General", new TwitterTemplate(consumerKey,
    // consumerSecret, accessToken, accessTokenSecret));
    // twitterTemplates.put("Field 1",
    // new TwitterTemplate(field1ConsumerKey, field1ConsumerSecret,
    // field1AccessToken, field1AccessTokenSecret));
    // twitterTemplates.put("Field 3", new
    // TwitterTemplate(field3ConsumerKey,
    // field3ConsumerSecret, field3AccessToken,
    // field3AccessTokenSecret));
    // twitterTemplates.put("Field 4", new
    // TwitterTemplate(field4ConsumerKey,
    // field4ConsumerSecret, field4AccessToken,
    // field4AccessTokenSecret));
    // twitterTemplates.put("Field 5", new
    // TwitterTemplate(field5ConsumerKey, field5ConsumerSecret,
    // field5AccessToken,
    // field5AccessTokenSecret));
    // twitterTemplates.put("Field 6",
    // new TwitterTemplate(field6ConsumerKey, field6ConsumerSecret,
    // field6AccessToken, field6AccessTokenSecret));
    // twitterTemplates.put("Field 7", new
    // TwitterTemplate(field7ConsumerKey, field7ConsumerSecret,
    // field7AccessToken,
    // field7AccessTokenSecret));
    // twitterTemplates.put("Field 8", new
    // TwitterTemplate(field8ConsumerKey, field8ConsumerSecret,
    // field8AccessToken,
    // field8AccessTokenSecret));
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
    // twitterTemplates.put("Field 19", new
    // TwitterTemplate(field19ConsumerKey, field19ConsumerSecret,
    // field19AccessToken, field19AccessTokenSecret));
    // twitterTemplates.put("MNP Park",
    // new TwitterTemplate(fieldMNPParkConsumerKey,
    // fieldMNPParkConsumerSecret, fieldMNPParkAccessToken,
    // fieldMNPParkAccessTokenSecret));
    return twitterTemplates;
  }
}
