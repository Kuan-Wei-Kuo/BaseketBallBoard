package com.kuo.basketballboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 2015/9/6.
 */
public class LineView extends View{

    private Point mSelectedPoint = null;

    public static final int POINT_ARRAY_SIZE = 7;
    public static final int C_START = 0;
    public static final int C_END = 1;
    public static final int C_CONTROL_1 = 2;
    public static final int C_CONTROL_2 = 3;

    private Point[] mPoints = new Point[POINT_ARRAY_SIZE];
    private Paint paint;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        mPoints[C_START] = new Point(0, height/2);
        mPoints[C_END] = new Point(width, height/2);
        mPoints[C_CONTROL_1] = new Point (width/4, (height/2) - (height/2));
        mPoints[C_CONTROL_2] = new Point(width - width/4, (height/2) + (height/2));

        Path path = new Path();
        path.moveTo(mPoints[C_START].x,mPoints[C_START].y);
        path.cubicTo(mPoints[C_CONTROL_1].x, mPoints[C_CONTROL_1].y, mPoints[C_CONTROL_2].x, mPoints[C_CONTROL_2].y, mPoints[C_END].x, mPoints[C_END].y);
        canvas.drawPath(path, paint);

    }

    public void setLineWidth(int i) {
        paint.setStrokeWidth(i);
        invalidate();
    }

    public void setLineColor(int i) {
        paint.setColor(i);
        invalidate();
    }

    public void setLineColor(int r, int g, int b) {
        paint.setColor(Color.rgb(r,g,b));
        invalidate();
    }
}
