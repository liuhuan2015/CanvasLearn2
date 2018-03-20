package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huan on 2018/3/19.
 * <p>
 * path.addRect(...)  其中的参数Path.Direction dir指定了点绘制的顺序（顺时针 or 逆时针）
 * <p>
 * // 第一类(基本形状)
 * <p>
 * // 圆形
 * public void addCircle (float x, float y, float radius, Path.Direction dir)
 * // 椭圆
 * public void addOval (RectF oval, Path.Direction dir)
 * // 矩形
 * public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
 * public void addRect (RectF rect, Path.Direction dir)
 * // 圆角矩形
 * public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
 * public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
 */

public class PathUseDetailAddXxx extends View {

    Paint mPaint;
    private int mWidth, mHeight;

    public PathUseDetailAddXxx(Context context) {
        this(context, null);
    }

    public PathUseDetailAddXxx(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailAddXxx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-100, 100);//设置这个可以让我们看到CW和CCW的区别
        canvas.drawPath(path, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
