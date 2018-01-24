package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/24 14:07
 * Description:画布缩放操作
 * <p>
 * 缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴
 * <p>
 * 当缩放比例为负数的时候会根据缩放中心轴进行翻转
 * <p>
 * 缩放也是可以叠加的
 */

public class ScaleView extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    RectF rectF = new RectF(0, -400, 400, 0);

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);
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
        //把坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);//绘制黑色矩形

        canvas.scale(0.5f, 0.5f);//画布缩放
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);//绘制蓝色矩形

        canvas.scale(-1f, -1f);//画布缩放
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);//绘制红色矩形

        canvas.scale(-0.5f, 0.5f, -100, 0);//画布缩放同时位移(缩放中心沿X轴方向位移100个像素,正负方向看当前坐标系方向)
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(10f);
        canvas.drawRect(rectF, mPaint);//绘制CYAN色矩形

    }
}
