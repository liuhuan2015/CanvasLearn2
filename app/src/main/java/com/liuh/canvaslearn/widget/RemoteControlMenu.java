package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;

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

        Region globalRegion = new Region(-w, -h, w, h);
        int minWidth = w > h ? h : w;
        minWidth *= 0.8;

        int br = minWidth / 2;
        RectF bigCircle = new RectF(-br, -br, br, br);

        int sr = minWidth / 4;
        RectF smallCircle = new RectF(-sr, -sr, sr, sr);

        float bigSweepAngle = 84;
        float smallSweepAngle = -84;

        //根据视图大小，初始化Path和Region
        center_p.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
        center.setPath(center_p, globalRegion);

        right_p.addArc(bigCircle, -42, bigSweepAngle);
        right_p.arcTo(smallCircle, 42, smallSweepAngle);
        right_p.close();
        right.setPath(right_p, globalRegion);

        down_p.addArc(bigCircle, 48, bigSweepAngle);
        down_p.arcTo(smallCircle, 132, smallSweepAngle);
        down_p.close();
        down.setPath(down_p, globalRegion);

        left_p.addArc(bigCircle, 138, bigSweepAngle);
        left_p.arcTo(smallCircle, 222, smallSweepAngle);
        left_p.close();
        left.setPath(left_p, globalRegion);

        up_p.addArc(bigCircle, 228, bigSweepAngle);
        up_p.arcTo(smallCircle, 312, smallSweepAngle);
        up_p.close();
        up.setPath(up_p, globalRegion);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        CanvasAidUtils.set2DAxisLength(500, 500, 700, 500);
        CanvasAidUtils.setLineColor(Color.CYAN);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);

        canvas.drawPath(center_p, mPaint);
        canvas.drawPath(right_p, mPaint);
        canvas.drawPath(down_p, mPaint);
        canvas.drawPath(left_p, mPaint);
        canvas.drawPath(up_p, mPaint);

    }
}
