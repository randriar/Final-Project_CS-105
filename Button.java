/*
This class will create buttons to visualize the data form the election
 */
import processing.core.PApplet;
public class Button {
  private int x, y, w, h;
  private PApplet p;
  private String name;

  public Button(int x, int y, int w, int h, String name, PApplet p) {
    this.name=name;
    this.x=x;
    this.y=y;
    this.p=p;
    this.w=w;
    this.h=h;
  }
  
  public Button(){
    
  }
  public void draw() {
    p.textAlign(p.LEFT, p.TOP);
    p.fill(250, 250, 150);
    p.rect(x, y, w, h);
    p.fill(0);
    p.textSize(11);
    p.text(name, x+3, y+10);
    p.fill(255);
  }
  public boolean mouseOver() {
    return p.mouseX>x && p.mouseX<x+w && p.mouseY>y && p.mouseY<y+h;
  }


  public boolean mouseClick() {
    return p.mousePressed && mouseOver();
  }
  public void disable() {
    
  }
}