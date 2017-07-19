import processing.core.PApplet;

public class ClintonT {
  private String []t;
  private String s;
  private Candidate Clinton;

  public ClintonT() {
    this.Clinton=Clinton;
    this.s="Clinton: "+ "\n";
    this.t=new String[Clinton.tweets.length];
    for (int i=0; i<Clinton.tweets.length; i++) {
      this.t[i]=s+"\n"+Clinton.tweets[i];
      for (int j=1; j<Clinton.tweets.length; j++) {
        if (Clinton.tweets[i].getDate().equals(Clinton.tweets[j].getDate())) {
          this.t[i]= this.t[i]+"\n"+Clinton.tweets[j]+"\n";
        }
      }
    }
  }
  public String getT(){
    return "q";
  }
}