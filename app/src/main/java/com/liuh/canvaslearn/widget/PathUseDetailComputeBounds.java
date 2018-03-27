package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/3/27 09:45
 * Description:Path使用中的计算边界(computeBounds(...))
 * <p>
 * void computeBounds (RectF bounds, boolean exact)
 */

public class PathUseDetailComputeBounds extends View {

    private Paint mPaint;

    private int mWidth, mHeight;


    public PathUseDetailComputeBounds(Context context) {
        this(context, null);
    }

    public PathUseDetailComputeBounds(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailComputeBounds(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
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
        RectF rect1 = new RectF();

        Path path = new Path();//创建Path并添加一些内容
        path.moveTo(0, 0);
        path.lineTo(100, -50);
        path.lineTo(100, 50);
        path.close();
        path.addCircle(-100, 0, 100, Path.Direction.CW);

        path.computeBounds(rect1, true);//测量Path

        canvas.drawPath(path, mPaint);//绘制path

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect1, mPaint);//绘制边界
    }
}
