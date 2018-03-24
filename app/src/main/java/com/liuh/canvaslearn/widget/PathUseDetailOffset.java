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
 * Created by huan on 2018/3/24.
 * <p>
 * public void offset (float dx, float dy)
 * public void offset (float dx, float dy, Path dst)
 * <p>
 * 这个的作用也很简单，就是对path进行一段平移，它和Canvas中的translate作用很像，但Canvas作用于整个画布，而path的offset只作用于当前path。
 * <p>
 * dst状态	       效果
 * dst不为空	       将当前path平移后的状态存入dst中，不会影响当前path
 * dat为空(null)	   平移将作用于当前path，相当于第一种方法
 */

public class PathUseDetailOffset extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathUseDetailOffset(Context context) {
        this(context, null);
    }

    public PathUseDetailOffset(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailOffset(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
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
        canvas.scale(1.0f, -1.0f);

        Path path = new Path();
        path.addCircle(0, 0, 100, Path.Direction.CW);//path中添加一个圆形

        Path dst = new Path();
        dst.addRect(-300, -300, 300, 300, Path.Direction.CW);//dst中添加一个矩形

        path.offset(300, 0, dst);//平移

        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(dst, mPaint);
    }
}
