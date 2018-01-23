package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/23 11:06
 * Description:绘制直线
 */

public class DrawLineView extends View {

    private Paint mPaint = new Paint();

    float[] mLineXYs = new float[]{
            100, 200, 200, 200,
            100, 300, 200, 300
    };

    public DrawLineView(Context context) {
        this(context, null);
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(300, 300, 500, 600, mPaint);
        canvas.drawLines(mLineXYs, mPaint);
    }
}
