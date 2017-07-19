import processing.core.PApplet;
/* a class that represents the tweet
 for each candidate
 It has a text, a date of publication and a tweet ID
 */

public class Tweet {
  // text in the tweet
  private String text;
  // publication date-including the time
  private String date;
  // tweet ID
  private String tweetId;

  /* constructor that creates a new tweet for a candidate's tweet*/
  public Tweet(String text, String tweetId, String date) {
    this.text=text;
    this.date=date;
    this.tweetId=tweetId;
  }

  // toString method that prints the tweet
  public String toString() {
    String s=this.text+"\n"+"ID: "+this.tweetId+"\n"+"date: "+this.date+"\n";
    return s;
  }
  
  /* get method for all the instance variables of */
  // get the text in the tweet
  public String getText() {
    return this.text;
  }
  // get the date of the publication of the tweet
  public String getDate() {
    return this.date;
  }
  // get the ID of the tweet
  public String getTweetId() {
    return this.tweetId;
  }
  //set date 
  public void setDate(String newDate){
    this.date=newDate;
  }
  
}