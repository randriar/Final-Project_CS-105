import processing.core.PApplet;
/* a class that represents a poll
 It has a poll name, a date, a sample size, a type of the voter, a marginal error, and a poll number
  all of them are represented in a table
*/

public class Poll{
  // name of the poll
   private String pollName;
   // date
   private String date;
   // size of the sample
   private String sampleSize;
   // type of voter: likely voter or registered voter
   private String typeOfVoter;
   // marginal error
   private String marginalError;
   // Cliton's result or poll number
   private int result;
 
   
   /* Constructor that creates a poll */
   public Poll(String pollName,String date,String sampleSize,String typeOfVoter,String marginalError,int result){     
     this.pollName=pollName;
     this.date=date;
     this.sampleSize=sampleSize;
     this.typeOfVoter=typeOfVoter;
     this.marginalError=marginalError;
     this.result=result;
 
   }
   
   /* get methods*/
   // name
   public String getPollName(){
     return this.pollName;
   }
   // date
   public String getDate(){
     return this.date;
   }
   // sample size
   public String getSampleSize(){
     return this.sampleSize;
   }
   // type of voter
   public String getTypeOfVoter(){
     return this.typeOfVoter;
   }
   // marginal error
   public String getMarginalError(){
     return this.marginalError;
   }
   // Clinton's result
   public int getResult(){
     return this.result;
   }
   /* a toString method*/
   public String toString(){
     String s="poll:"+this.pollName+"  date:"+this.date+"  sample:"+this.sampleSize+"  typeOfVoters:"+this.typeOfVoter+"  marginalError:"+this.marginalError+"  result:"+this.result+"\n";
     return s;
   }
}