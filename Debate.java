import processing.data.IntDict;
import processing.core.PApplet;
/* a class that represents a debate transcript of the election
 It has the date of the debate, the name of the participants in capitals,
 and the entire transcripts with the speaker mentioned in capitals before the transcript
 */
public class Debate {
  // the date of the debate
  private String date;
  // the names of all the candidates who participate
  private String namesOfCandidates;
  // the transcript with the name of the speaker before it
  private String transcript;

  /* constructor that creates the debate in a specific date */
  public Debate(String date, String namesOfCandidates, String transcript) {
    this.date=date;
    this.namesOfCandidates=namesOfCandidates;
    this.transcript=transcript;
  }
  // toString method that prints the entire debate
  public String toString() {
    String s=this.date+"\n"+"Participants:"+this.namesOfCandidates+"\n"+this.transcript+"\n";
    return s;
  }

  /* get methods*/
  // date of the debate
  public String getDate() {
    return this.date;
  }
  // names of the participants
  public String getNamesOfCandidates() {
    return this.namesOfCandidates;
  }
  // the transcript of the entire debate
  public String getTranscript() {
    return this.transcript;
  }

  /*method that split a debate
   and count the words' frequency
   */
  public String frequencyDebate( PApplet p) {
    String delimiters=" \',.?!;:[]/*><+-_0123456789@#$%^&";// delimiters
    String exception="e,clinton,trump,ve,t,s,re,a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at,be,because,been,but,by,can,cannot,could,dear,did,do,does,either,else,ever,every,for,from,get,got,had,has,have,he,her,hers,him,his,how,however,i,if,in,into,is,it,its,just,least,let,like,likely,may,me,might,most,must,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,she,should,since,so,some,than,that,the,their,them,then,there,these,they,this,tis,to,too,twas,us,wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,yet,you,your";
    String[]wordsExcept=exception.split(",");
    int count=0;
    int count1=0;
    //int index=0;
    String newTranscript=this.getTranscript();
    String[]trans=p.splitTokens(newTranscript, delimiters);


    for (int j=0; j<trans.length; j++) {
      trans[j]=trans[j].toLowerCase();
    }
    for (int a=0; a<trans.length; a++) {
      for (int b=0; b<wordsExcept.length; b++) {
        if (trans[a].equals(wordsExcept[b])) {
          count++;
        } else
        {
          trans[a]=trans[a];
        }
      }
    }


    String[]trans1=new String[trans.length-count];
    int value=0;
    //p.println(trans1.length);
    for (int x=0; x<trans.length; x++) {
      for (int y=0; y<wordsExcept.length; y++) {
        if (trans[x].equals(wordsExcept[y])) {
          value=1;
          break;
        } else {
          value=2;
        }
      }
      if (value==2) {
        trans1[count1]=trans[x];
        count1++;
      }
    }
    IntDict counts=new IntDict();
    for (int i=0; i<trans1.length; i++) {
      String word=trans1[i];
      if (counts.hasKey(word)) {
        counts.increment(word);
      } else {
        counts.set(word, 1);
      }
    }



    counts.sortValuesReverse();
    String [] keys=counts.keyArray();
    int [] c=new int[keys.length];
    for ( int k=0; k<keys.length; k++) {
      c[k]=counts.get(keys[k]);
    }
    //p.println(counts[0]);
    String print=" WORDS AND THEIR FREQUENCY:"+"\n";  
    String []finalArray=new String[keys.length];
    for (int l=0; l<finalArray.length; l++) {
      finalArray[l]=keys[l]+": "+c[l];
    }
    for ( int kk=0; kk<7; kk++) {
      print=print+"\n"+finalArray[kk];
    }

    return print;
  }
}