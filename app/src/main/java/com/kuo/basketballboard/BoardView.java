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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2015/8/17.
 */
public class BoardView extends RelativeLayout {

    private String GREY_800 = "#424242";

    private boolean ACTION_DRAW_LINE = false;

    private float mX;
    private float mY;

    private Paint line;
    private Paint userLine;
    private Path path = null;
    private ArrayList<ColorPath> colorPaths = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private Map<Path, Integer> colorsMap = new HashMap<Path, Integer>();

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

        userLine = new Paint();
        userLine.setColor(Color.parseColor(GREY_800));
        userLine.setStyle(Paint.Style.STROKE);
        userLine.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();
        int lineColor = userLine.getColor();
        /*Place*/
        drawMultipleSupportBoard(width, height, canvas);

        if (paths.size() > 0) {
            for (Path p : paths)
            {
                userLine.setColor(colorsMap.get(p));
                canvas.drawPath(p, userLine);
            }
            userLine.setColor(lineColor);
            canvas.drawPath(path, userLine);
        }

    }

    public void setLineColor(int i) {
        userLine.setColor(i);
    }

    public void setLineColor(int r, int g, int b) {
        userLine.setColor(Color.rgb(r, g, b));
    }

    public void setLineWidth(int i) {
        userLine.setStrokeWidth(i);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(ACTION_DRAW_LINE) {
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
                    paths.add(path);
                    colorsMap.put(path, userLine.getColor()); // store the color of mPath
                    path = new Path();
                    path.reset();
                    invalidate();
                    break;
            }
        }
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

    public void setDrawLine(boolean i) {
        this.ACTION_DRAW_LINE  = i;
    }

    public boolean getDrawLine() {
        return this.ACTION_DRAW_LINE;
    }

}
