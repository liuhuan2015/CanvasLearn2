package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/23 15:20
 * Description:Paint的一些基础用法学习
 * <p>
 * STROKE                //描边
 * FILL                  //填充
 * FILL_AND_STROKE       //描边加填充
 * <p>
 * 关于Paint的内容也是有不少的，这些只是冰山一角
 */

public class PaintUseLearnView extends View {

    private Paint mPaint = new Paint();

    public PaintUseLearnView(Context context) {
        this(context, null);
    }

    public PaintUseLearnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(40f);

        //描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 200, 100, mPaint);

        //填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 500, 100, mPaint);

        //描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, mPaint);

    }
}
