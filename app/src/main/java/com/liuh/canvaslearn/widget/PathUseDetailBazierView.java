package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huan on 2018/3/25.
 * Path使用中的二阶贝塞尔曲线
 * 二阶贝塞尔曲线由两个数据点，一个控制点组成，对应方法是quadTo(...)
 * <p>
 * 下面的代码实现是先绘制了三个点，然后绘制了两条辅助线，然后使用quadTo(...)绘制贝塞尔曲线
 * <p>
 * 通过onTouchEvent方法获取手指触摸的位置，更新控制点的位置，然后重绘
 */

public class PathUseDetailBazierView extends View {

    private Paint mPaint;
    private int centerX, centerY;

    private PointF start, end, control;

    public PathUseDetailBazierView(Context context) {
        this(context, null);
    }

    public PathUseDetailBazierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailBazierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18
                , getResources().getDisplayMetrics()));

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        //初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;

        end.x = centerX + 200;
        end.y = centerY;

        control.x = centerX;
        control.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }

}
