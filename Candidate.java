 import processing.core.PApplet;
import java.util.*;
import java.text.*;
/* Candidate class for all the candidates on the election 
 */
public class Candidate {
  private String nameOfTheCandidate;
  public Tweet[] tweets;
  public Debate[] debates;
  public Poll[]polls;
  public SearchPopularity[]popularities;
  private int countTweet, countPopularity, countDebate, countPoll;
  private String[]cand={"RADDATZ:", "COOPER:", "QUESTION:", "CLINTON:", "TRUMP:", "WALLACE:", "HOLT:"};


  /* constructor that creates a candidate of the election
   it takes the name of a candidate and a PApplet
   all the counters(countTweet,countPopularity,countDebate,countPoll) are initialized to 0
   */
  public Candidate(String nameOfTheCandidate, PApplet p) {
    this.nameOfTheCandidate=nameOfTheCandidate;
    this.countPopularity=0;
    this.countTweet=0;
    this.countDebate=0;
    this.countPoll=0;
    /* set the length of the array of debatea
     equals to the number of debates for each candidate
     */
    if (debates==null) {
      if (this.nameOfTheCandidate=="Clinton") {
        debates=new Debate[226];
      }
      if (this.nameOfTheCandidate=="Trump") {
        debates=new Debate[342];
      }
    }
    String[]aaa=p.loadStrings("polling.csv");
    if (this.polls==null) {
      this.polls=new Poll[aaa.length-1];
    }
    //p.println(debates.length);
  }
  /* methods that load all the information */

  /* method to loads the tweets of each candidate*/
  public void loadTweet(String fileName, PApplet p) {
    String[] lines=p.loadStrings(fileName);
    //p.println(lines.length);
    if (this.tweets==null) {
      this.tweets= new Tweet[lines.length/3];
    }
    //p.println(tweets.length);
    for (int i=0; i<lines.length; i+=3) {
      Tweet a=new Tweet(lines[i], lines[i+1], lines[i+2]);
      tweets[this.countTweet]=a;
      this.countTweet++;
    }
  }
  /* method that loads the popularity of the candidates*/
  public void loadPopularity(String fileName, String candidate, PApplet p) {
    String[] lines=p.loadStrings(fileName);
    if (this.popularities==null) {
      this.popularities=new SearchPopularity[lines.length];
    }
    for (int i=0; i<lines.length; i++) {
      String[]line=lines[i].split(",");
      if (candidate=="Clinton") {
        SearchPopularity sp=new SearchPopularity(line[0], Integer.parseInt(line[2]));
        this.popularities[this.countPopularity]=sp;
        this.countPopularity++;
      }
      if (candidate=="Trump") {
        SearchPopularity sp=new SearchPopularity(line[0], Integer.parseInt(line[1]));
        this.popularities[this.countPopularity]=sp;
        this.countPopularity++;
      }
    }
  }
  /* method that loads the poll's data*/
  public void loadPoll(String fileName, PApplet p) {
    String[]lines=p.loadStrings(fileName);
    for (int i=1; i<lines.length; i++) {
      String[]line=lines[i].split(",");
      if (this.nameOfTheCandidate=="Clinton") {
        Poll a=new Poll( line[0], line[2], line[3], line[4], line[5], Integer.parseInt(line[6]));
        polls[countPoll]=a;
        countPoll++;
      }
      if (this.nameOfTheCandidate=="Trump") {
        Poll b=new Poll(line[0], line[2], line[3], line[4], line[5], Integer.parseInt(line[7]));
        polls[countPoll]=b;
        countPoll++;
      }
      //  Poll a=new Poll(line[0], line[2], Integer.parseInt(line[3]), line[4], Float.parseFloat(line[5]), Integer.parseInt(line[7]));
    }
  }


  /* method that load the debate file*/
  public void loadDebate(String fileName, PApplet p) {
    String[]line=p.loadStrings(fileName);
    String[]lines=new String[line.length];
    lines[0]=line[0];
    lines[1]=line[1];
    int countLines=2;
    for (int j=2; j<line.length; j++) {
      boolean found=false;
      for (int c=0; c<this.cand.length; c++) {
        if (line[j].contains(this.cand[c])) {
          found=true;
        }
      }
      //s p.println("\n*********"+found+" "+line[j]);
      if (!found) {
        String con=lines[countLines-1] + " " + line[j];
        lines[countLines-1]=con;
        //System.out.println("\nConcatenated onto lines[ " + (countLines-1) + " ]: "+ lines[countLines-1]);
      } else {
        lines[countLines]= line[j];
        countLines++;
        //p.println("\nNew entry in lines[ " + (countLines-1) + " ]: " + lines[countLines-1]);
      }
    }
    //p.println(countLines);
    for (int i=0; i<countLines; i++) {
      if (lines[i].contains(this.nameOfTheCandidate.toUpperCase()+":")) {
        Debate d=new Debate(lines[0], lines[1], lines[i]);
        this.debates[this.countDebate]=d;
        this.countDebate++;
      }
    }
    //p.println(countDebate+" "+this.debates.length+" "+countLines+" "+lines.length);
  }

  /* a toString method for the candidate class*/
  public String toString() {
    String s=this.nameOfTheCandidate+":"+"\n";
    for (int i=0; i<this.popularities.length; i++) {
      s=s+this.popularities[i];
    }
    for (int j=0; j<this.tweets.length; j++) {
      s=s+this.tweets[j]+"\n";
    }
    return s;
  }
  /* method that print on the screen the longest debate transcript */
  public Debate longestDebate() {
    Debate longest=this.debates[0];
    for (int i=1; i<this.debates.length; i++) {
      if (this.debates[i].getTranscript().length()> longest.getTranscript().length()) {
        longest=this.debates[i];
      }
    }
    //printing the longest debate in the screen
    return longest;
  }

    /*set the dates of the tweets in the format:"M/d/yy"*/
  public void setDateOfTweets() {
    for (int i=0; i<this.tweets.length; i++) {
      Date myDate =new Date(this.tweets[i].getDate());
      String date= new SimpleDateFormat("M/d/yy").format(myDate);
      this.tweets[i].setDate(date);
    }
  }

  /* get the total popularity of each candidate */
  public int totalPopularity(){
    int total=0;
    for(int i=0;i<this.popularities.length;i++){
      total=total+this.popularities[i].getPopularity();
    }
    return total;
  }
  
  
}