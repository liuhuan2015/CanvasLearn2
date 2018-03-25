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
 * Path使用中的三阶贝塞尔曲线
 * <p>
 * 三阶贝塞尔曲线由两个数据点，两个控制点组成
 * <p>
 * 下面的代码实现了通过手指切换控制点，通过效果可以看到当我们控制不同的控制点时三阶贝塞尔曲线的变化
 * <p>
 * 对应的方法是Path的cubicTo(...)方法
 */

public class PathUseDetailBazierThirdOrderView extends View {

    private Paint mPaint;

    private int centerX, centerY;

    private PointF start, end, control1, control2;

    private boolean mode = true;//控制点：true，control1；false，control2


    public PathUseDetailBazierThirdOrderView(Context context) {
        this(context, null);
    }

    public PathUseDetailBazierThirdOrderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailBazierThirdOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18
                , getResources().getDisplayMetrics()));

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
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

        control1.x = centerX;
        control1.y = centerY - 100;

        control2.x = centerX;
        control2.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸位置更新控制点，并进行重绘
        if (mode) {
            control1.x = event.getX();
            control1.y = event.getY();
        } else {
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }
}
