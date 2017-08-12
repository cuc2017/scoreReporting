package com.cuc2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class TestLogin {

  @Test
  public void test() {
    HttpClient client = null;
    try {
      CookieStore cookieStore = new BasicCookieStore();
      BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
      cookie.setDomain("80.172.224.48");
      cookie.setPath("/cuc2017-test ");
      cookieStore.addCookie(cookie);
      client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
      String cookieReturned = getCookie(client);
      HttpPost post = new HttpPost("http://80.172.224.48/cuc2017-test/scorekeeper/?view=addscoresheet");
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
      nameValuePairs.add(new BasicNameValuePair("add", "Save goal"));
      nameValuePairs.add(new BasicNameValuePair("goal", "396"));
      nameValuePairs.add(new BasicNameValuePair("pass", "397"));
      nameValuePairs.add(new BasicNameValuePair("team", "H"));
      nameValuePairs.add(new BasicNameValuePair("timemm", "12"));
      nameValuePairs.add(new BasicNameValuePair("timess", "0"));
      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      // post.addHeader("Cookie", "CUC2017test=m3hu7f47bs0d3qem281sshmto2");
      // post.addHeader("Cookie", cookie);
      post.addHeader("User-Agent",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.");

      post.addHeader("Origin", "http://80.172.224.48");
      post.addHeader("Upgrade-Insecure-Requests", "1");
      post.addHeader("Referer", "http://80.172.224.48/cuc2017-test/scorekeeper/?view=addscoresheet&game=21");
      HttpResponse response = client.execute(post);

      final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

      String line = "";
      while ((line = rd.readLine()) != null) {
        System.out.println(line);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    } finally {
      client.getConnectionManager().shutdown();
    }
  }

  private String getCookie(HttpClient client)
      throws UnsupportedEncodingException, IOException, ClientProtocolException {

    HttpGet get = new HttpGet("http://80.172.224.48/cuc2017-test/scorekeeper/?view=login");
    HttpResponse response1 = client.execute(get);
    final BufferedReader rd1 = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
    String line2 = "";
    while ((line2 = rd1.readLine()) != null) {
      System.out.println(line2);
    }
    HttpPost post = new HttpPost("http://80.172.224.48/cuc2017-test/scorekeeper/?view=login");
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    nameValuePairs.add(new BasicNameValuePair("login", "Login"));
    nameValuePairs.add(new BasicNameValuePair("mypassword", "cucuc"));
    nameValuePairs.add(new BasicNameValuePair("myusername", "score"));
    post.addHeader("User-Agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.");

    post.addHeader("Origin", "http://80.172.224.48");
    post.addHeader("Connection", "keep-alive");
    post.addHeader("Cache-Control", "max-age=0");
    post.addHeader("Referer", "http://80.172.224.48/cuc2017-test/scorekeeper/?view=login");
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    HttpResponse response = client.execute(post);
    String cookie = null;
    if (response.getStatusLine().getStatusCode() == 302) {
      for (Header header : response.getAllHeaders()) {
        if (header.getName().equalsIgnoreCase("Set-Cookie")) {
          System.out.println("Cookie is: " + header.getValue());

          cookie = header.getValue().substring(0, header.getValue().indexOf(';'));
        }
      }
    }

    final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    String line = "";
    while ((line = rd.readLine()) != null) {
      System.out.println(line);
    }
    return cookie;
  }

}
