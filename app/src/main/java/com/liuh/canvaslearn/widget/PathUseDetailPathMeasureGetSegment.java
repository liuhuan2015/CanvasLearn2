package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/3/28 11:18
 * Description:PathMeasure的getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
 * <p>
 * 返回值(boolean)	判断截取是否成功	true 表示截取成功，结果存入dst中，false 截取失败，不会改变dst中内容
 * startD	    开始截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
 * stopD   	结束截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
 * dst	    截取的 Path 将会添加到 dst 中	注意: 是添加，而不是替换
 * startWithMoveTo	起始点是否使用 moveTo	用于保证截取的 Path 第一个点位置不变
 * <p>
 * 如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，如果 startWithMoveTo 为 false，
 * 则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，以保证 dst 的连续性。
 * <p>
 * startWithMoveTo取值	  主要功用
 * true	                  保证截取得到的 Path 片段不会发生形变
 * false	              保证存储截取片段的 Path(dst) 的连续性
 */

public class PathUseDetailPathMeasureGetSegment extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathUseDetailPathMeasureGetSegment(Context context) {
        this(context, null);
    }

    public PathUseDetailPathMeasureGetSegment(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailPathMeasureGetSegment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);//顺时针的矩形
//        mPaint.setColor(Color.BLACK);
//        canvas.drawPath(path, mPaint);

        Path dst = new Path();
        dst.lineTo(-300, -300);

        PathMeasure pathMeasure = new PathMeasure(path, false);//将 Path 与 PathMeasure 关联

        pathMeasure.getSegment(200, 600, dst, false);//<--- 截取一部分 不使用 startMoveTo, 保持 dst 的连续性

        canvas.drawPath(dst, mPaint);
    }
}
