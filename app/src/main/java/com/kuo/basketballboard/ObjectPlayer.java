package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ObjectPlayer extends View{
  
    int id;
    float cx;
    float cy;
    String tag;

    public ObjectPlayer(Context context) {
        this(context, null);
    }

    public ObjectPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float supportPoint = width * 0.093f;
    }

    public void drawPlayer(float cx, float cy, float raduis, Paint paint, Canvas canvas) {
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
