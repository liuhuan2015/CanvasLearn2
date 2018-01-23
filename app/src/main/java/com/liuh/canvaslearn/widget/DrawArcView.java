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
 * Date: 2018/1/23 14:54
 * Description:绘制圆弧
 * <p>
 * DrawArc(...)中的参数解释
 * startAngle  // 开始角度
 * sweepAngle  // 扫过角度
 * useCenter   // 是否使用中心
 * <p>
 * 可以发现使用了中心点之后绘制出来类似于一个扇形，而不使用中心点则是圆弧起始点和结束点之间的连线加上圆弧围成的图形。
 */

public class DrawArcView extends View {

    private Paint mPaint = new Paint();
    RectF rectF = new RectF(100, 100, 600, 600);
    RectF rectF1 = new RectF(100, 700, 600, 1200);

    public DrawArcView(Context context) {
        this(context, null);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);
        //绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 0, 90, false, mPaint);

        //绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1, mPaint);
        //绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF1, 0, 90, true, mPaint);

    }
}
