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
 * Created by huan on 2018/2/22.
 * Path的lineTo(...)方法：在坐标点之间画一条line
 * Path的moveTo(...)方法：移动下一次操作的起点位置
 * Path的setLastPoint(...)方法：设置之前操作的最后一个点的位置
 * Path的close方法：用于连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形.
 * 注意：close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。
 * 如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么也不做
 */

public class PathUseDetailFirstGroupView extends View {
    Paint mPaint;
    private int mWidth, mHeight;

    public PathUseDetailFirstGroupView(Context context) {
        this(context, null);
    }

    public PathUseDetailFirstGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

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
//lineTo
//        path.lineTo(200, 200);
//        path.lineTo(200, 0);

//moveTo
//        path.lineTo(200, 200);
//        path.moveTo(200, 100);
//        path.lineTo(200, 0);

        //setLastPoint
        path.lineTo(200, 200);
        path.setLastPoint(200, 100);
        path.lineTo(200, 0);
        path.close();

        canvas.drawPath(path, mPaint);
    }
}
