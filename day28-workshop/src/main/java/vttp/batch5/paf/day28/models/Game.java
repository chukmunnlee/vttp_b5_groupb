package vttp.batch5.paf.day28.models;

import java.util.List;
import java.util.LinkedList;

public class Game {

   private int gameId; // gid
   private String name; // name
   private int ranking; // ranking
   private String url; // url
   private String image; // image
   private List<Comment> comments = new LinkedList<>();

   public int getGameId() { return gameId; }
   public void setGameId(int gameId) { this.gameId = gameId; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public int getRanking() { return ranking; }
   public void setRanking(int ranking) { this.ranking = ranking; }

   public String getUrl() { return url; }
   public void setUrl(String url) { this.url = url; }

   public String getImage() { return image; }
   public void setImage(String image) { this.image = image; }

   public List<Comment> getComments() { return this.comments; }
   public void setComments(List<Comment> comments) { this.comments = comments; }
   public void addComment(Comment comment) { this.comments.add(comment); }

   @Override
   public String toString() {
      return "Game [gameId=" + gameId + ", name=" + name + ", ranking=" + ranking + "]";
   }

}
