package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ObjectPlayer extends View{

    private String BLUE_300 = "#64B5F6";

    private Paint player;

    public ObjectPlayer(Context context) {
        this(context, null);
    }

    public ObjectPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);

        player = new Paint();
        player.setColor(Color.parseColor(BLUE_300));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        canvas.drawCircle(width/2, height/2, width/2, player);

    }

    private float dX;
    private float dY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                dX = getX() - event.getRawX();
                dY = getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;

        }

        return true;
    }
}
