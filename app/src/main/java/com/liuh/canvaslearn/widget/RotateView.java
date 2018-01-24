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
 * Date: 2018/1/24 15:44
 * Description:画布旋转
 * <p>
 * 旋转也是可以叠加的
 */

public class RotateView extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    RectF rectF = new RectF(0, -400, 400, 0);

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
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
        //将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawRect(rectF, mPaint);

        canvas.rotate(180);//旋转180度,默认以坐标系原点为旋转中心
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

    }
}
