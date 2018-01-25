package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Date: 2018/1/25 11:48
 * Description:drawPicture的使用
 * <p>
 * 录制内容，即将一些Canvas操作用Picture存储起来，录制的内容是不会直接显示在屏幕上的，只是存储起来了而已。
 * <p>
 * 想要将Picture中的内容显示出来就需要手动调用播放(绘制)
 * <p>
 * 手动绘制有三种方式
 * 1.使用Picture提供的draw方法绘制(这种方式在低版本系统上绘制后可能会影响Canvas的状态,并且可操作性较弱(可以简单理解为对绘制结果可控程度),一般不会使用)
 * 2.使用Canvas提供的drawPicture方法绘制(不会影响Canvas的状态,并且可操作性较强)
 * 3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制(不会影响Canvas的状态,并且可操作性较强)
 */

public class DrawPictureView extends View {

    private Picture mPicture = new Picture();

    RectF rectF = new RectF();
    PictureDrawable drawable;

    public DrawPictureView(Context context) {
        this(context, null);
    }

    public DrawPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        recording();//调用录制
    }

    private void recording() {
        //开始录制
        Canvas canvas = mPicture.beginRecording(500, 500);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, paint);

        //结束录制
        mPicture.endRecording();

        //包装成为PictureDrawable
        drawable = new PictureDrawable(mPicture);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), mPicture.getHeight()));
        rectF.set(0, 0, mPicture.getWidth(), 200);//绘制的内容会根据选区进行缩放
        canvas.drawPicture(mPicture, rectF);


        Log.e("--------------", "----mPicture.getHeight():" + mPicture.getHeight());
        drawable.setBounds(0, 300, 250, mPicture.getHeight() + 300);//setBounds会限制绘制区域
        drawable.draw(canvas);

    }
}
