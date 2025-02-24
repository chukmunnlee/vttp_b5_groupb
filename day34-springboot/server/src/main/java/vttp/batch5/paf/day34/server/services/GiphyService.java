package vttp.batch5.paf.day34.server.services;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

  public static final String GIPHY_SEARCH = "https://api.giphy.com/v1/gifs/search";

  @Value("${giphy.apikey}")
  private String apiKey;

  public List<String> search(String q, int limit, String rating) {
    String url = UriComponentsBuilder
        .fromUriString(GIPHY_SEARCH)
        .queryParam("q", q.replaceAll(" ", "+"))
        .queryParam("limit", limit)
        .queryParam("rating", rating)
        .queryParam("api_key", apiKey)
        .toUriString();

    RequestEntity<Void> req = RequestEntity.get(url).build();
    RestTemplate template = new RestTemplate();

    ResponseEntity<String> resp = template.exchange(req, String.class);

    JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
    JsonObject payload = reader.readObject();
    JsonArray data = payload.getJsonArray("data");

    return data.stream()
      .map(doc -> doc.asJsonObject())
      .map(doc -> doc.getJsonObject("images").getJsonObject("fixed_height").getString("url"))
      .toList();
  }
}
