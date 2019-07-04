package com.cuc2017;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class GetScores {

  @Test
  public void testGetScores() throws Exception {
    HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    String baseSite = "http://80.172.224.48/cuc2018/";
    String site = baseSite + "?view=games&season=CUC2018&filter=tournaments&group=all";
    List<String> games = getGamesForSite(client, site);
    File output = new File("scores.csv");
    try (Writer ow = new FileWriter(output)) {
      ow.write("game,home,away,time,duration\n");
      for (String game : games) {
        gameInfo(client, ow, baseSite + game);
      }
    }
  }

  private void gameInfo(HttpClient client, Writer writer, String game) throws Exception {
    String gameNumber = game.substring(game.lastIndexOf("=") + 1, game.length());
    Element content = getContent(client, game);
    Elements tables = content.select("table");
    Element scoreTable = tables.get(6);
    for (Element scoreRow : scoreTable.select("tr")) {
      Elements tds = scoreRow.select("td");
      if (tds.size() <= 0) {
        continue;
      }
      String score = tds.get(0).text();
      String[] scores = score.split("-");
      String home = scores[0].trim();
      String away = scores[1].trim();
      String time = tds.get(3).text();
      String duration = tds.get(4).text();
      writer.write(gameNumber + "," + home + "," + away + "," + time + "," + duration + "\n");
      // System.out.println(
      // "Game: " + gameNumber + " home: " + home + " away: " + away + " time: "
      // + time + " duration: " + duration);
    }

  }

  private List<String> getGamesForSite(HttpClient client, String site) throws Exception {
    List<String> games = new ArrayList<>();

    Element content = getContent(client, site);
    Elements tables = content.select("table");
    for (Element table : tables) {
      for (Element gameRow : table.select("tr")) {
        Elements tds = gameRow.select("td");
        if (tds.size() <= 0) {
          continue;
        }
        Element gameUrlElement = gameRow.select("a[href]").first();
        if (gameUrlElement != null) {
          String gameUrl = gameUrlElement.attr("href");
          games.add(gameUrl);
        }
      }
    }
    return games;
  }

  protected Element getContent(HttpClient client, String site) throws IOException, ClientProtocolException {
    HttpGet get = new HttpGet(site);
    HttpResponse response1 = client.execute(get);
    InputStream is = response1.getEntity().getContent();
    Document page = Jsoup.parse(is, StandardCharsets.UTF_8.displayName(), site);
    Element content = page.select("div.content").first();
    return content;
  }
}
