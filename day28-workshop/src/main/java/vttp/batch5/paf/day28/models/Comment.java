package vttp.batch5.paf.day28.models;

public class Comment {

   private String user; // user
   private int rating; // rating
   private String text; // c_text

   public String getUser() { return user; }
   public void setUser(String user) { this.user = user; }

   public int getRating() { return rating; }
   public void setRating(int rating) { this.rating = rating; }

   public String getText() { return text; }
   public void setText(String text) { this.text = text; }

   @Override
   public String toString() {
      return "Comment [user=" + user + ", rating=" + rating + ", text=" + text + "]";
   }

}
