package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/23 10:49
 * Description:绘制点
 */

public class DrawPointView extends View {

    private Paint mPaint = new Paint();

    float[] points = new float[]{
            500, 500,
            500, 600,
            500, 700
    };

    public DrawPointView(Context context) {
        this(context, null);
    }

    public DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
    }

    //警告的意思是在onDraw()函数中最好不要去创建对象，否则就会提示下面的警告信息
    //Avoid object allocations during draw/layout operations
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPoint(200, 200, mPaint);
        canvas.drawPoints(points, mPaint);
    }
}
