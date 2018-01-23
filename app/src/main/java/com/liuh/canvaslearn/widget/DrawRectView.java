package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/23 11:14
 * Description:绘制矩形
 * 确定一个矩形最少需要四个数据,就是对角线的两个点的坐标,这里一般采用的是左上角和右下角的两个点的坐标
 */

public class DrawRectView extends View {

    private Paint mPaint = new Paint();

    public DrawRectView(Context context) {
        this(context, null);
    }

    public DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一种绘制矩形方法
        canvas.drawRect(100, 100, 800, 400, mPaint);

        //第二种绘制矩形方法
        Rect rect = new Rect(100, 500, 800, 800);
        canvas.drawRect(rect, mPaint);

        //第三种绘制矩形方法
        RectF rectf = new RectF(100, 900, 800, 1200);
        canvas.drawRect(rectf, mPaint);


    }
}
