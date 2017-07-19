/* This Project will allow the user to visualize some of the presidential election data
 Instructions: -click on the "tweets" button to see how the tweets posted by each candidate affected the search popularity result
 -the "return" button will allow the user to return to the main page.
 */
import processing.core.PApplet;
import java.util.*;
import java.text.*;

Candidate Clinton, Trump;
String [] t;
String [] c;
Button b1, b2, b3, bGraph, bClinton, bTrump, b3Plus, b4Minus, bDonald, bHillary,bb;
int value, val;
int  debateNum1, debateNum2, candidate;
PImage imgClinton, imgTrump;
Date myDate;

void setup() {
  //fullScreen();
  size(1300, 700);
  background(30);  
  /*create the object Clinton as a candidate and load all its files*/
  Clinton=new Candidate("Clinton", this);
  Clinton.loadTweet("ClintonTweets.txt", this);
  Clinton.loadPopularity("trump_hillary_search_popularity.csv", "Clinton", this);

  Clinton.loadDebate("Sep_26_Hofstra.txt", this);
  Clinton.loadDebate("Oct_9_Missouri.txt", this);
  Clinton.loadDebate("Oct_19_LasVegas.txt", this);
  Clinton.loadPoll("polling.csv", this);  

  /*create the object Trump as a candidate and load all its files*/
  Trump=new Candidate("Trump", this);
  Trump.loadTweet("TrumpTweets.txt", this);
  Trump.loadPopularity("trump_hillary_search_popularity.csv", "Trump", this);
  Trump.loadDebate("Sep_26_Hofstra.txt", this);
  Trump.loadDebate("Oct_9_Missouri.txt", this);
  Trump.loadDebate("Oct_19_LasVegas.txt", this);
  Trump.loadPoll("polling.csv", this);



  /* create the buttons: "tweets" and "return" at the top left of the screen 
   in order for the user to use them
   */
  b1=new Button(20, 20, 50, 30, "TWEETS", this);
  b2=new Button(20, 55, 50, 30, "DEBATE", this);
  b3=new Button(20, 90, 50, 30, "RETURN", this);
  bb=new Button();
  bb=b1;
  //bGraph=new Button(150, 75, 950, 550, "Graph", this);
  Clinton.setDateOfTweets();//set the dates of clinton's tweets in the format:"M/d/yy"
  Trump.setDateOfTweets();//set the dates of trump's tweets in the format:"M/d/yy"
  c=tweetsAccDate(Clinton, "CLINTON");// class Clinton's tweets by their date
  t=tweetsAccDate(Trump, "TRUMP");// class Trump's tweet by their date 
  value=0;
  val=0;
  // load Clinton and Trump's images
  imgClinton=loadImage("Clinton.jpg");
  imgTrump=loadImage("Trump.jpg");
  // create clinton and trump buttons to refer to their image
  bClinton= new Button(800, 525, 150, 150, "C", this);
  bTrump= new Button(1000, 525, 150, 150, "T", this);
  debateNum1=0;
  debateNum2=1;
  b3Plus=new Button(20, 125, 50, 30, "Next", this);
  b4Minus=new Button(20, 160, 50, 30, "Previous", this);
  bDonald=new Button(20, 195, 50, 30, "Donald", this);
  bHillary=new Button(20, 230, 50, 30, "Hillary", this);

  //Trump.debates[1].frequencyDebate(this);
}

void draw() {
  background(30);
  if (debateNum1<0)
  {
    debateNum1=0;
  }
  if (debateNum2<0)
  {
    debateNum2=0;
  }
  if (debateNum1>Trump.debates.length)
  {
    debateNum1=Trump.debates.length-1;
  }

  if (debateNum2>Clinton.debates.length)
  {
    debateNum2=Clinton.debates.length-1;
  }
  if (value==2) {
    b3Plus.draw();
    b4Minus.draw();
    bDonald.draw();
    bHillary.draw();
    b3.draw();
    //Restricting the index of the debates array for each candidate

    //If Trump displays the candidates debate, the frequency of words and words in it 
    if (candidate==0)
    {


      if (Trump.debates[debateNum1].getTranscript().length()>199)
      {
        fill(255);
        textSize(15);

        text(Trump.debates[debateNum1].frequencyDebate(this), 100, 100, 500, 500);
        rect(590, 100, 510, 500);
        textSize(11);
        fill(0);
        text(Trump.debates[debateNum1].getTranscript(), 600, 110, 500, 500);

        noFill();
      }
    }
    //If clinton displays the candidates debate, the frequency of words and words in it 
    if (candidate==1)
    {


      if (Clinton.debates[debateNum2].getTranscript().length()>199)
      {
        fill(255);
        textSize(15);
        text(Clinton.debates[debateNum2].frequencyDebate(this), 100, 100, 500, 500);
        rect(590, 100, 510, 500);
        textSize(11);
        fill(0);
        text(Clinton.debates[debateNum2].getTranscript(), 600, 110, 500, 500);

        noFill();
      }
    }
  }



if (value==0) {
  graphPopularity(150, 75, 950, 550);
  textAlign(LEFT, TOP);
  b1.draw();
  b2.draw();
  b3.draw();
}
if (value==1) {
  graphPopularity(775, 50, 500, 400);
  image(imgClinton, 800, 525, 150, 150);
  image(imgTrump, 1000, 525, 150, 150);
  b1.draw();
  b2.draw();
  b3.draw();

  int xcoord=500/Clinton.popularities.length;
  int index=round ((mouseX-775)/xcoord);
  textAlign(CENTER, CENTER);
  textSize(15);
  text("Select a candidate"+"\n"+"Then move the mouse in the graph in order to visualize the tweets"+"\n"+"The tweets will change according to the date you point on the popularity graph", 65, 25, 675, 665);
  for (int i=0; i<Clinton.tweets.length; i++) {
    if (val==1 && index>=0 && index<Clinton.popularities.length && mouseY<450 && mouseY>50 && Clinton.tweets[i].getDate().equals(Clinton.popularities[index].getDate())) {
      b1.draw();
      b2.draw();
      b3.draw();
      background(30);
      graphPopularity(775, 50, 500, 400);
      image(imgClinton, 800, 525, 150, 150);
      fill(255, 0, 0);
      text("CLINTON: "+Clinton.popularities[index].getPopularity(), 850, 510);
      image(imgTrump, 1000, 525, 150, 150);
      fill(0, 255, 0);
      text("TRUMP: "+Trump.popularities[index].getPopularity(), 1050, 510);
      textSize(12);
      fill(255);
      text(c[i], 70, 10, 675, 665);
    }
  }

  for (int i=0; i<Trump.tweets.length; i++) {
    if (val==2 && index>=0 && index<Trump.popularities.length && mouseY<450 && mouseY>50 && Trump.tweets[i].getDate().equals(Trump.popularities[index].getDate())) {
      b1.draw();
      b2.draw();
      b3.draw();
      background(30);
      graphPopularity(775, 50, 500, 400);
      image(imgClinton, 800, 525, 150, 150);
      fill(255, 0, 0);
      text("CLINTON: "+Clinton.popularities[index].getPopularity(), 850, 510);
      image(imgTrump, 1000, 525, 150, 150);
      fill(0, 255, 0);
      text("TRUMP: "+Trump.popularities[index].getPopularity(), 1050, 510);
      textSize(12);
      fill(255);
      text(t[i], 70, 10, 675, 665);
    }
  }
  //textAlign(LEFT, TOP);
  b1.draw();
  b2.draw();
}
}


/* this part will al]ow the user to use the methods that the application provided
 */
void mousePressed() { 
  
  // if the button b1 is pressed, value=1  
  if (b1.mouseClick()) {
    value=1;
  }
  if (b2.mouseClick()) {
    b1=b3;
    value=2;
  }
  // if the button b2 is pressed, value=0 -return button 
  if (b3.mouseClick()) {
    b1=bb;
    value=0;
  }

  if (bClinton.mouseClick()) {
    val=1;
  }
  if (bTrump.mouseClick()) {
    val=2;
  }
  if (b3Plus.mouseClick()) {
    if (candidate==0 && debateNum1<Trump.debates.length)
    {

      debateNum1++;
      while (Trump.debates[debateNum1].getTranscript().length()<200 )
      {
        debateNum1++;
      }
    }
    if (candidate==1 && debateNum2<Clinton.debates.length)
    {
      debateNum2++;
      while (Clinton.debates[debateNum2].getTranscript().length()<200 )
      {
        debateNum2++;
      }
    }
  }
  if (b4Minus.mouseClick()) {

    if (candidate==0 && debateNum1>0)
    {
      debateNum1--;
      while (Trump.debates[debateNum1].getTranscript().length()<199 && debateNum1<Trump.debates.length )
      {
        debateNum1--;
      }
    }

    if (candidate==1 && debateNum2>1)
    {
      debateNum2--;
      while (Clinton.debates[debateNum2].getTranscript().length()<199 && debateNum2<Clinton.debates.length )
      {
        debateNum2--;
      }
    }
  }

  if (bHillary.mouseClick()) {
    candidate=1;
  }
  if (bDonald.mouseClick()) {
    candidate=0;
  }
}

/* function that graph the popularity of Clinton and Trump
 given the x and y coordinate of the reference point of the rectangle(starting point)
 and the width and height of the rectangle
 */
public void graphPopularity(int x, int y, int w, int h) {
  rect(x, y, w, h); 
  textAlign(CENTER, CENTER);
  //fill(255);
  int xcoord=w/Clinton.popularities.length;
  int ycoord=h/100;
  // index of the graph
  int kk=100;
  stroke(200);
  for (int z=y; z<=y+h && kk>=0; z=z+(h/10))
  { 
    line(x, z, x+w, z);// draw lines for the separation of the y-axis
    textSize(13);// set the testSize
    textAlign(RIGHT, CENTER);
    fill(250);
    text(kk, x, z);// print the percentage next to the line of separation
    kk-=10;
  }
  textAlign(CENTER, BOTTOM);
  text("POPULARITY STATISTIC", x+(w/2), y); 
  // graph of the popularity
  for (int i=0; i<Clinton.popularities.length-1; i++) {
    // stroke(250, 150, 10);
    stroke(150);
    line((i*xcoord)+x, y+h-(Clinton.popularities[i].getPopularity()*ycoord), ((i+1)*xcoord)+x, y+h-Clinton.popularities[i+1].getPopularity()*ycoord);
    line((i*xcoord)+x, y+h-(Trump.popularities[i].getPopularity())*ycoord, ((i+1)*xcoord)+x, y+h-Trump.popularities[i+1].getPopularity()*ycoord);
  }
  fill(250);
  int index=round ((mouseX-x)/xcoord);
  if (mouseX>=x && mouseX<=x+w && mouseY>y-50 && mouseY<y+50+h && index<Trump.popularities.length ) {
    line(mouseX, y, mouseX, y+h);// verticale line
    textAlign(LEFT, TOP);
    textSize(17);
    text(Clinton.popularities[index].getDate(), mouseX, y+h);// print the date at the bottom of the rectangle 
    for (int j=0; j<index; j++) {
      stroke(255, 0, 0);
      fill(255, 0, 0);
      line((j*xcoord)+x, y+h-(Clinton.popularities[j].getPopularity()*ycoord), ((j+1)*xcoord)+x, y+h-Clinton.popularities[j+1].getPopularity()*ycoord);
      ellipse(index*xcoord+x, y+h-Clinton.popularities[index].getPopularity()*ycoord, 4, 4);// index of Clinton's popularity
      stroke(0, 255, 0);
      fill(0, 255, 0);
      line((j*xcoord)+x, y+h-(Trump.popularities[j].getPopularity())*ycoord, ((j+1)*xcoord)+x, y+h-Trump.popularities[j+1].getPopularity()*ycoord);
      ellipse(index*xcoord+x, y+h-Trump.popularities[index].getPopularity()*ycoord, 4, 4);// index of Trump's popularity
    }
    textAlign(RIGHT, BOTTOM);
    fill(255, 0, 0);
    text("Clinton: "+Clinton.popularities[index].getPopularity(), x+w, y-25);
    fill(0, 255, 0);
    text("Trump: "+Trump.popularities[index].getPopularity(), x+w, y);
  } else {
    for (int i=0; i<Clinton.popularities.length-1; i++) {
      stroke(255, 0, 0);
      line((i*xcoord)+x, y+h-(Clinton.popularities[i].getPopularity()*ycoord), ((i+1)*xcoord)+x, y+h-Clinton.popularities[i+1].getPopularity()*ycoord);
      stroke(0, 255, 0);
      line((i*xcoord)+x, y+h-(Trump.popularities[i].getPopularity())*ycoord, ((i+1)*xcoord)+x, y+h-Trump.popularities[i+1].getPopularity()*ycoord);
    }
    textAlign(RIGHT, BOTTOM);
    fill(0, 255, 0);
    text("Trump: "+Trump.totalPopularity(), x+w, y);
    fill(255, 0, 0);
    text("Clinton: "+Clinton.totalPopularity(), x+w, y-25);
  }
  textAlign(CENTER, CENTER);
  textSize(13);
  fill(255);
  stroke(200);
}

/* a function that will class the tweets written in the same day ,given the name of the candidate
 It will return an array of String of the tweets published in the same day */
public String [] tweetsAccDate(Candidate candidate, String cand) {
  String[]t=new String[Clinton.tweets.length];
  String s=  cand+":"+ "\n";
  for (int i=0; i<candidate.tweets.length; i++) {
    t[i]=s+"\n"+candidate.tweets[i];
    for (int j=1; j<candidate.tweets.length; j++) {
      String date0=candidate.tweets[i].getDate();
      String date1=candidate.tweets[j].getDate();
      if (date0.equals(date1) && i!=j) {
        t[i]= t[i]+"\n"+candidate.tweets[j];
      }
    }
  }
  //textSize(5);
  return t;
}