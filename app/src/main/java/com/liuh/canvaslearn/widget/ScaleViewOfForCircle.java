package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Date: 2018/1/24 14:43
 * Description:这是一个使用for循环进行画布缩放并drawRect的例子
 */

public class ScaleViewOfForCircle extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    RectF rectF = new RectF();

    public ScaleViewOfForCircle(Context context) {
        this(context, null);
    }

    public ScaleViewOfForCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.e("------------", "---mWidth:" + mWidth + "---mHeight:" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        rectF.set(-400, -400, 400, 400);

        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }
    }
}
