package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by User on 2015/8/17.
 */
public class BoardView extends RelativeLayout {

    private String GREY_800 = "#424242";

    private boolean ACTION_MOVE = false;
    private boolean ACTION_DOWN = false;

    private int mov_x;//聲明起點座標
    private int mov_y;
    private float curX;
    private float curY;
    private float mX;
    private float mY;

    private Paint line;
    private Canvas canvas;
    private Bitmap bitmap;
    private Path path = null;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        path = new Path();

        line = new Paint();
        line.setColor(Color.parseColor(GREY_800));
        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth(5);
    }

    public void onCreateDrawLineCanvas(Bitmap bitmap) {
        canvas = new Canvas();
        this.bitmap = bitmap;
        canvas.setBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();



        /*Place*/
        drawMultipleSupportBoard(width, height, canvas);

        if (path != null) {
            canvas.drawPath(path, line);
        }
        /*if(ACTION_DOWN) {
            canvas.drawPoint(mov_x, mov_y, line);
        }else if(ACTION_MOVE){
            canvas.drawLine(mov_x, mov_y, curX, curY, line);
        }*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x, y;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                mX = x;
                mY = y;
                path.moveTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();

                final float previousX = mX;
                final float previousY = mY;

                float cX = (x + previousX) / 2;
                float cY = (y + previousY) / 2;

                //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
                path.quadTo(previousX, previousY, cX, cY);

                //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
                mX = x;
                mY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                ACTION_DOWN = false;
                ACTION_MOVE = false;
                break;
        }

        mov_x = (int) event.getX();
        mov_y = (int) event.getY();

        return true;
    }

    private void drawMultipleSupportBoard(int width, int height, Canvas canvas) {
        
        float supportPoint = width * 0.093f;
        RectF rectF = new RectF(width / 2 - supportPoint, height/2 + height / 4 - supportPoint, width / 2 + supportPoint, height / 2 + height / 4 + supportPoint);
        RectF rectF_1 = new RectF(width / 2 - supportPoint, height / 4 - supportPoint, width / 2 + supportPoint, height / 4 + supportPoint);
        
        canvas.drawRect(supportPoint, supportPoint, width - supportPoint, height - supportPoint, line);
        canvas.drawLine(supportPoint, height / 2, width - supportPoint, height / 2, line);
        canvas.drawCircle(width / 2, height / 2, supportPoint, line);
        
        canvas.drawRect(width/2 - (supportPoint + (supportPoint/2)), supportPoint, width/2 + (supportPoint + (supportPoint/2)), height/4, line);
        canvas.drawArc(rectF_1, 0, 180, true, line);
        canvas.drawLine(width/2 - supportPoint/2, supportPoint + (supportPoint/2), width/2 + supportPoint/2, supportPoint + (supportPoint/2), line);
        canvas.drawCircle(width/2, supportPoint + (supportPoint/2) + supportPoint/4, supportPoint/4, line);
        
        canvas.drawRect(width/2 - (supportPoint + (supportPoint/2)), height/2+height/4, width/2 + (supportPoint + (supportPoint/2)), height-supportPoint, line);
        canvas.drawArc(rectF, 0, -180, true, line);
        canvas.drawLine(width/2 - supportPoint/2, height - (supportPoint + (supportPoint/2)), width/2 + supportPoint/2, height - (supportPoint + (supportPoint/2)), line);
        canvas.drawCircle(width/2, height - (supportPoint + (supportPoint/2) + supportPoint/4), supportPoint/4, line);
    
        
    }
    
    /*Just support the 1920*1080.*/
    private void drawDefaultBoard(int width, int height, Canvas canvas) {

        RectF rectF = new RectF(width/2-100, height/2+height/4-100, width/2+100, height/2+height/4+100);
        RectF rectF_1 = new RectF(width/2-100, height/4-100, width/2+100, height/4+100);

        canvas.drawRect(100, 100, width - 100, height - 100, line);
        canvas.drawCircle(width / 2, height / 2, 100, line);
        canvas.drawLine(100, height / 2, width - 100, height / 2, line);


        canvas.drawRect(width/2-150, 100, width/2+150, height/4, line);
        canvas.drawArc(rectF_1, 0, 180, true, line);
        canvas.drawLine(width/2-50, 150, width/2+50, 150, line);
        canvas.drawCircle(width/2, 180, 30, line);

        canvas.drawRect(width/2-150, height/2+height/4, width/2+150, height-100, line);
        canvas.drawArc(rectF, 0, -180, true, line);
        canvas.drawLine(width/2-50, height-150, width/2+50, height-150, line);
        canvas.drawCircle(width/2, height-180, 30, line);
        
    }

}
