package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/23 14:07
 * Description:绘制椭圆
 * <p>
 * 绘制椭圆实际上就是绘制一个矩形的内切圆形,如果传递进来的是一个正方形,那么绘制出来的实际上就是一个圆
 */

public class DrawOvalView extends View {

    private Paint mPaint = new Paint();

    RectF rectF = new RectF(100, 100, 800, 400);

    public DrawOvalView(Context context) {
        this(context, null);
    }

    public DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一种绘制椭圆方式
        canvas.drawOval(rectF, mPaint);

        //第二种绘制椭圆方式
        canvas.drawOval(100, 500, 800, 800, mPaint);
    }
}
