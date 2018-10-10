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
 * Date: 2018/10/10 14:19
 * Description: 人脸识别框
 */
public class FaceRecognizeFrameView extends View {

    Paint mPaint;
    private int mWidth, mHeight;

    public FaceRecognizeFrameView(Context context) {
        this(context, null);
    }

    public FaceRecognizeFrameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceRecognizeFrameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    Path path = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        path.moveTo(150, -300);
        path.lineTo(300, -300);
        path.lineTo(300, -150);

        path.moveTo(300, 150);
        path.lineTo(300, 300);
        path.lineTo(150, 300);

        path.moveTo(-150, 300);
        path.lineTo(-300, 300);
        path.lineTo(-300, 150);

        path.moveTo(-300, -150);
        path.lineTo(-300, -300);
        path.lineTo(-150, -300);

        canvas.drawPath(path, mPaint);

    }
}
