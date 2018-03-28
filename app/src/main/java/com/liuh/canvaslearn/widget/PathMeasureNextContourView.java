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
 * Created by huan on 2018/3/28.
 * <p>
 * PathMeasure的nextContour()
 * <p>
 * Path 可以由多条曲线构成，但不论是 getLength , getgetSegment 或者是其它方法，都只会在其中第一条线段上运行，
 * 而这个 nextContour 就是用于跳转到下一条曲线的方法，如果跳转成功，则返回 true， 如果跳转失败，则返回 false。
 * <p>
 * 结论：
 * 1.曲线的顺序与 Path 中添加的顺序有关。
 * 2.getLength 获取到到是当前一条曲线分长度，而不是整个 Path 的长度。
 * 3.getLength 等方法是针对当前的曲线(其它方法请自行验证)。
 */

public class PathMeasureNextContourView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathMeasureNextContourView(Context context) {
        this(context, null);
    }

    public PathMeasureNextContourView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureNextContourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
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
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);//添加小矩形
//        canvas.drawPath(path, mPaint);
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);//添加大矩形

        canvas.drawPath(path, mPaint);//绘制的时候好像只会绘制最后面的那个Rect

        PathMeasure pathMeasure = new PathMeasure(path, false);//将Path和PathMeasure关联

        float len1 = pathMeasure.getLength();

        pathMeasure.nextContour();//跳转到下一条路径

        float len2 = pathMeasure.getLength();

        Log.e("----------", "len1 : " + len1);
        Log.e("----------", "len2 : " + len2);

    }
}
