package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/4/10 11:37
 * Description:
 */

public class RemoteControlMenu extends View {

    private int mWidth, mHeight;
    private Paint mPaint;

    Path up_p, down_p, left_p, right_p, center_p;
    Region up, down, left, right, center;

    Matrix mMapMatrix = null;

    int CENTER = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int LEFT = 4;
    int touchFlag = -1;
    int currentFlag = -1;

//    MenuListener mListener = null;

    int mDefaultColor = 0xFF4E5268;
    int mTouchedColor = 0xFFDF9C81;

    public RemoteControlMenu(Context context) {
        this(context, null);
    }

    public RemoteControlMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(mDefaultColor);
        mPaint.setAntiAlias(true);

        mMapMatrix = new Matrix();

        up_p = new Path();
        down_p = new Path();
        left_p = new Path();
        right_p = new Path();
        center_p = new Path();

        up = new Region();
        down = new Region();
        left = new Region();
        right = new Region();
        center = new Region();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mMapMatrix.reset();





    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);


    }
}
