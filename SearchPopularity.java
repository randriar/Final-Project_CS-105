import processing.core.PApplet;
/* a class of popularity
 It searches the popularity of each candidates which is given by a relative number maximum of 100
 */
public class SearchPopularity {
  // the date of the research
  private String date;
  // the result of the popularity (<= 100)
  public int popularity;

  /* constructor that creates a table which represents the date and the popularity of each candidates*/
  public SearchPopularity(String date, int popularity){
    this.date=date;
    if (popularity>=0 && popularity<=100){
      this.popularity=popularity;
    }else System.out.println(" Popularity invalid.");
  }
  
  /* get methods */
  // the day of the popularity
  public String getDate(){
    return this.date;
  }
  // the popularity after the result
  public int getPopularity(){
    return this.popularity;
  }
  /* a toString method */
  public String toString(){
    String s=this.date+" popularity:"+this.popularity+"\n";
    return s;
  }
}