package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by User on 2015/9/19.
 */
public class CircleTextView extends TextView {

    private Paint paint;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.RED_300));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = 0;

        if(width > height) {
            radius = height/2;
        }else {
            radius = width/2;
        }

        canvas.drawCircle(width/2, height/2, radius, paint);

    }
}
