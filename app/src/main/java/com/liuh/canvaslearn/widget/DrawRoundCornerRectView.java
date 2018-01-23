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
 * Date: 2018/1/23 13:44
 * Description:圆角矩形
 */

public class DrawRoundCornerRectView extends View {

    private Paint mPaint = new Paint();


    public DrawRoundCornerRectView(Context context) {
        this(context, null);
    }

    public DrawRoundCornerRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    //rx,ry这两个参数是圆角处椭圆的两个半径
    //实际上在rx为宽度的一半，ry为高度的一半时，刚好是一个椭圆
    @Override
    protected void onDraw(Canvas canvas) {

        //第一种绘制圆角矩形方式
        RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawRoundRect(rectF, 30, 30, mPaint);

        //第二种绘制圆角矩形方式,这个方法是在api21的时候才添加上去的,所以我们一般都是使用的第一种方法
        canvas.drawRoundRect(100, 500, 800, 800, 350, 150, mPaint);

    }
}
