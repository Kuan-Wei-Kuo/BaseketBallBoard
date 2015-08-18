package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 2015/8/17.
 */
public class BoardView extends View {

    private String GREY_800 = "#424242";

    private Paint line;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        line = new Paint();
        line.setColor(Color.parseColor(GREY_800));
        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();


        /*Place*/
        drawMultipleSupportBoard(width, height, canvas);
        
        
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
