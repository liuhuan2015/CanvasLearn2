package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Date: 2018/3/28 09:58
 * Description:PathMeasure的构造方法
 * <p>
 * 1.我们将 Path 与两个的 PathMeasure 进行关联，并给 forceClosed 设置了不同的状态，
 * 之后绘制再绘制出来的 Path 没有任何变化，所以与 Path 与 PathMeasure进行关联并不会影响 Path 状态。
 * <p>
 * 2.我们可以看到，设置 forceClosed 为 true 的方法比设置为 false 的方法测量出来的长度要长一点，
 * 这是由于 Path 没有闭合的缘故，多出来的距离正是 Path 最后一个点与最开始一个点之间点距离。
 * forceClosed 为 false 测量的是当前 Path 状态的长度， forceClosed 为 true，
 * 则不论Path是否闭合测量的都是 Path 的闭合长度。
 */

public class PathMeasureConstructorView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathMeasureConstructorView(Context context) {
        this(context, null);
    }

    public PathMeasureConstructorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureConstructorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
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

        Path path = new Path();

        path.lineTo(0, 200);
        path.lineTo(250, 200);
        path.lineTo(200, 0);

        PathMeasure pathMeasure1 = new PathMeasure(path, true);
        PathMeasure pathMeasure2 = new PathMeasure(path, false);

        Log.e("PathMeasureView", "forceColsed=true------->" + pathMeasure1.getLength());
        Log.e("PathMeasureView", "forceColsed=false------->" + pathMeasure2.getLength());

        canvas.drawPath(path, mPaint);
    }
}
