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
 * Date: 2018/1/24 16:59
 * Description:Canvas之错切操作
 * <p>
 * skew这里翻译为错切，错切是特殊类型的线性变换,它只有一种方法
 * <p>
 * public void skew (float sx, float sy)
 * <p>
 * sx:将画布在x轴方向上倾斜相应的角度，sx为倾斜角度的tan值
 * sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值
 * <p>
 * 错切也是可以叠加的,调用次序不同,绘制结果也会不同
 */

public class SkewView extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;
    RectF rectF = new RectF();


    public SkewView(Context context) {
        super(context);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
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
        //绘制矩形区域
        rectF.set(0, 0, 200, 200);
        canvas.drawRect(rectF, mPaint);

        //进行错切和绘制
        canvas.skew(1, 0);
//        canvas.skew(0, 1);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);
    }
}
