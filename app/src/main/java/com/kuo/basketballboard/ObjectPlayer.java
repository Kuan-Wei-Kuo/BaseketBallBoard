package com.kuo.basketballboard;

public class ObjectPlayer {
  
  int id;
  float cx;
  float cy;
  String tag;
  
  Context context;
  
  public ObjectPlayer(Context context) {
    this.context = context;
  }
  
  public void drawPlayer(flaot cx, float cy, raduis, Paint paint, Canvas canvas) {
    canvas.drawCircle(cx, cy, raduis, paint);
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public float getCx() {
    return this.cx;
  }
  
  public float getCy() {
    return this.cy;
  }
  
}
