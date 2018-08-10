package com.cuc2017;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.cuc2017.model.Division;
import com.cuc2017.model.GameOrderDetails;
import com.cuc2017.model.Team;
import com.cuc2017.service.GameServiceImpl;

public class TestLogin {

  @Test
  public void testFindGameId() throws Exception {
    HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    login(client);
    Division openDivision = new Division("Junior Women", 2, "");

    Team team1 = new Team(openDivision, "TORO", 1);
    Team team2 = new Team(openDivision, "Bonfire: Junior Women", 2);

    GameOrderDetails id = findGameIdFromUltimatCanadaSite(client, team1, team2);
    System.out.println("Id is: " + id);

    id = findGameIdFromUltimatCanadaSite(client, team2, team1);
    System.out.println("Id is: " + id);

    // updatePlayers(client, id);
    // saveScore(client, id);

  }

  private GameOrderDetails findGameIdFromUltimatCanadaSite(HttpClient client, Team team1, Team team2) throws Exception {
    // try {
    URL url = new URL(GameServiceImpl.GAMES_CARD);
    HttpGet get = new HttpGet(GameServiceImpl.GAMES_CARD);
    HttpResponse response1 = client.execute(get);
    InputStream is = response1.getEntity().getContent();
    // final BufferedReader rd1 = new BufferedReader(new
    // InputStreamReader(response1.getEntity().getContent()));

    Document page = Jsoup.parse(is, StandardCharsets.UTF_8.displayName(), GameServiceImpl.GAMES_CARD);
    Element content = page.select("div.content").first();
    Elements tables = content.select("table");
    for (Element table : tables) {
      for (Element gameRow : table.select("tr")) {
        Elements tds = gameRow.select("td");
        if (tds.size() <= 0) {
          continue;
        }
        String teamName1 = tds.get(1).text();
        String teamName2 = tds.get(3).text();
        if (teamName1 != null && teamName2 != null) {
          if (teamName1.equalsIgnoreCase(team1.getName()) && teamName2.equalsIgnoreCase(team2.getName())) {
            System.out.println("Game row is: " + gameRow);
            if (tds.get(4).text().contains("0") && tds.get(6).text().contains("0")) {
              return findGameNumber(team1, team2, tds.get(8));
            }
          } else if (teamName1.equalsIgnoreCase(team2.getName()) && teamName2.equalsIgnoreCase(team1.getName())) {
            System.out.println("Game row is: " + gameRow);
            if (tds.get(4).text().contains("0") && tds.get(6).text().contains("0")) {
              return findGameNumber(team2, team1, tds.get(8));
            }
          }
        }
      }
    }
    return null;
    // } catch (Exception e) {
    // // log.error("Could not load players ", e);
    // }
  }

  private GameOrderDetails findGameNumber(Team team1, Team team2, Element gameTableRow) {
    Element gameUrlElement = gameTableRow.select("a[href]").first();
    String gameUrl = gameUrlElement.attr("href");
    int gameNumberIndex = gameUrl.indexOf("game=");
    String gameNumberString = gameUrl.substring(gameNumberIndex + 5);
    return new GameOrderDetails(team1, team2, Integer.valueOf(gameNumberString));
  }

  public void testWithHttpUrlConnection() throws Exception {
    URL url = new URL("http://80.172.224.48/cuc2017-test/scorekeeper/?view=login");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
    OutputStream os = connection.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
    osw.write("login: Login\nmypassword: cucuc\nmyusername: score");
    osw.flush();
    osw.close();
    os.close(); // don't forget to close the OutputStream
    connection.connect();

    // read the inputstream and print it
    String result;
    BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    int result2 = bis.read();
    while (result2 != -1) {
      buf.write((byte) result2);
      result2 = bis.read();
    }
    result = buf.toString();
    System.out.println(result);
  }

  public void saveScore(HttpClient client, GameOrderDetails gameOrderDetails) {
    try {
      if (gameOrderDetails == null || gameOrderDetails.getGameNumber() <= 0) {
        // log.warn("Could not save game details");
        return;
      }
      String uri = GameServiceImpl.SCORESHEET + gameOrderDetails.getGameNumber();
      HttpPost post = new HttpPost(uri);
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
      // nameValuePairs.add(new BasicNameValuePair("add", "Save goal"));
      // nameValuePairs.add(new BasicNameValuePair("goal", "243"));
      // nameValuePairs.add(new BasicNameValuePair("pass", "242"));
      // nameValuePairs.add(new BasicNameValuePair("team", "H"));
      // nameValuePairs.add(new BasicNameValuePair("timemm", "5"));
      // nameValuePairs.add(new BasicNameValuePair("timess", "0"));
      nameValuePairs.add(new BasicNameValuePair("isongoing", "on"));
      nameValuePairs.add(new BasicNameValuePair("save", "Save scores"));
      nameValuePairs.add(new BasicNameValuePair("pass1", "4"));
      nameValuePairs.add(new BasicNameValuePair("goal1", "1"));
      nameValuePairs.add(new BasicNameValuePair("team1", "H"));
      nameValuePairs.add(new BasicNameValuePair("time1", "2.15"));
      nameValuePairs.add(new BasicNameValuePair("pass2", "2"));
      nameValuePairs.add(new BasicNameValuePair("goal2", "4"));
      nameValuePairs.add(new BasicNameValuePair("team2", "H"));
      nameValuePairs.add(new BasicNameValuePair("time2", "3.15"));
      nameValuePairs.add(new BasicNameValuePair("pass3", "6"));
      nameValuePairs.add(new BasicNameValuePair("goal3", "5"));
      nameValuePairs.add(new BasicNameValuePair("team3", "A"));
      nameValuePairs.add(new BasicNameValuePair("time3", "4.15"));
      nameValuePairs.add(new BasicNameValuePair("pass4", "XX"));
      nameValuePairs.add(new BasicNameValuePair("goal4", "4"));
      nameValuePairs.add(new BasicNameValuePair("team4", "H"));
      nameValuePairs.add(new BasicNameValuePair("time4", "5.15"));

      HttpResponse response = doPost(client, uri, post, nameValuePairs);

      final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

      String line = "";
      while ((line = rd.readLine()) != null) {
        System.out.println(line);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  protected HttpResponse doPost(HttpClient client, String uri, HttpPost post, List<NameValuePair> nameValuePairs)
      throws UnsupportedEncodingException, IOException, ClientProtocolException {
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    post.addHeader("Origin", "http://80.172.224.48");
    post.addHeader("Upgrade-Insecure-Requests", "1");
    post.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
    post.addHeader("User-Agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
    post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    post.addHeader("Referer", uri);
    // post.addHeader("Referer",
    // "http://80.172.224.48/cuc2017-test/scorekeeper/?view=addscoresheet&game=8");
    post.addHeader("Accept-Encoding", "gzip, deflate");
    post.addHeader("Accept-Language:", "en-US,en;q=0.9");
    HttpResponse response = client.execute(post);
    System.out.println("Response code is: " + response.getStatusLine().getStatusCode());
    return response;
  }

  private void updatePlayers(HttpClient client, GameOrderDetails gameOrderDetails) {
    try {
      if (gameOrderDetails == null || gameOrderDetails.getGameNumber() <= 0) {
        // log.warn("Could not save game details");
        return;
      }
      String uri = GameServiceImpl.ADD_PLAYERS + gameOrderDetails.getGameNumber();
      HttpResponse response = null;
      try {
        HttpPost post = new HttpPost(uri);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
        // nameValuePairs.add(new BasicNameValuePair("add", "Save goal"));
        // nameValuePairs.add(new BasicNameValuePair("goal", "243"));
        // nameValuePairs.add(new BasicNameValuePair("pass", "242"));
        // nameValuePairs.add(new BasicNameValuePair("team", "H"));
        // nameValuePairs.add(new BasicNameValuePair("timemm", "5"));
        // nameValuePairs.add(new BasicNameValuePair("timess", "0"));
        nameValuePairs.add(new BasicNameValuePair("awaycheck[]", "74"));
        nameValuePairs.add(new BasicNameValuePair("awaycheck[]", "75"));
        nameValuePairs.add(new BasicNameValuePair("awaycheck[]", "77"));
        nameValuePairs.add(new BasicNameValuePair("homecheck[]", "368"));
        nameValuePairs.add(new BasicNameValuePair("homecheck[]", "369"));
        nameValuePairs.add(new BasicNameValuePair("homecheck[]", "370"));
        nameValuePairs.add(new BasicNameValuePair("p368", "1"));
        nameValuePairs.add(new BasicNameValuePair("p369", "2"));
        nameValuePairs.add(new BasicNameValuePair("p370", "4"));
        nameValuePairs.add(new BasicNameValuePair("p74", "5"));
        nameValuePairs.add(new BasicNameValuePair("p75", "6"));
        nameValuePairs.add(new BasicNameValuePair("p77", "7"));
        nameValuePairs.add(new BasicNameValuePair("save", "Save"));
        nameValuePairs.add(
            new BasicNameValuePair("backurl", "http://80.172.224.48/cuc2017-test//?view=user/addscoresheet&game=29"));

        response = doPost(client, uri, post, nameValuePairs);

        final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
          System.out.println(line);
        }
      } finally {
        HttpClientUtils.closeQuietly(response);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void testGetCookie() throws Exception {

    HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    login(client);
    // saveScore(client);
  }

  private void login(HttpClient client) throws UnsupportedEncodingException, IOException, ClientProtocolException {

    // HttpGet get = new HttpGet(GameServiceImpl.LOGIN);
    // HttpResponse response1 = client.execute(get);
    // final BufferedReader rd1 = new BufferedReader(new
    // InputStreamReader(response1.getEntity().getContent()));
    // String line2 = "";
    // while ((line2 = rd1.readLine()) != null) {
    // System.out.println(line2);
    // }
    HttpPost post = new HttpPost(GameServiceImpl.LOGIN);
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    nameValuePairs.add(new BasicNameValuePair("login", "Login"));
    nameValuePairs.add(new BasicNameValuePair("mypassword", "repeek"));
    nameValuePairs.add(new BasicNameValuePair("myusername", "score"));
    post.addHeader("User-Agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.");

    post.addHeader("Origin", "http://80.172.224.48");
    post.addHeader("Connection", "keep-alive");
    post.addHeader("Cache-Control", "max-age=0");
    post.addHeader("Referer", "http://80.172.224.48/cuc2017-test/scorekeeper/?view=login");
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    HttpResponse response = client.execute(post);
    System.out.println("Response code is: " + response.getStatusLine().getStatusCode());
  }

}
