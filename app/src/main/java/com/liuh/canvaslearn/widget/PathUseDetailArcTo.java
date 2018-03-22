package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huan on 2018/3/22.
 * Path的arcTo(...)
 */

public class PathUseDetailArcTo extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathUseDetailArcTo(Context context) {
        this(context, null);
    }

    public PathUseDetailArcTo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailArcTo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.scale(1.0f, -1.0f);//y轴坐标进行反转

        Path path = new Path();
        path.lineTo(100, 100);

        RectF oval = new RectF(0, 0, 300, 300);//圆弧的外切矩形
        path.arcTo(oval, 0, 270);
//        path.arcTo(oval, 0, 270, false);//和上面一句等价

        canvas.drawPath(path, mPaint);
    }
}
