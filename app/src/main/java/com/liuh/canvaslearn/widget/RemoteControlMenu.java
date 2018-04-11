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
import android.view.MotionEvent;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;

/**
 * Date: 2018/4/10 11:37
 * Description:遥控器的上下左右界面
 * <p>
 * 一.绘制上下左右键,其中的角度值是需要计算下的
 * right_p.addArc(bigCircle, -42, bigSweepAngle);
 * right_p.arcTo(smallCircle, 40, smallSweepAngle);
 * right_p.close();
 * right.setPath(right_p, globalRegion);
 * <p>
 * 二.判断区域,使用了Region类的contains方法
 * if (center.contains(x, y)) {
 * return CENTER;
 * } else if (right.contains(x, y)) {
 * return RIGHT;
 * } else if (down.contains(x, y)) {
 * return DOWN;
 * } else if (left.contains(x, y)) {
 * return LEFT;
 * } else if (up.contains(x, y)) {
 * return UP;
 * }
 * return -1;
 * <p>
 * 三.因为画布坐标系和屏幕原始坐标系不一致,这里也做了手指触摸点的坐标位置转换
 * onTouchEvent方法中:
 * float[] pts = new float[2];
 * pts[0] = event.getRawX();
 * pts[1] = event.getRawY();
 * mMapMatrix.mapPoints(pts);
 * <p>
 * onDraw方法中
 * //获取测量矩阵(逆矩阵)
 * if (mMapMatrix.isIdentity()) {
 * canvas.getMatrix().invert(mMapMatrix);
 * }
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

    MenuListener mListener = null;

    int mDefaultColor = 0xFF4E5268;
    int mTouchedColor = 0xFFDF9C81;

    public void setmListener(MenuListener mListener) {
        this.mListener = mListener;
    }

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
        float smallSweepAngle = -80;

        //根据视图大小，初始化Path和Region
        center_p.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
        center.setPath(center_p, globalRegion);

        right_p.addArc(bigCircle, -42, bigSweepAngle);
        right_p.arcTo(smallCircle, 40, smallSweepAngle);
        right_p.close();
        right.setPath(right_p, globalRegion);

        down_p.addArc(bigCircle, 48, bigSweepAngle);
        down_p.arcTo(smallCircle, 130, smallSweepAngle);
        down_p.close();
        down.setPath(down_p, globalRegion);

        left_p.addArc(bigCircle, 138, bigSweepAngle);
        left_p.arcTo(smallCircle, 220, smallSweepAngle);
        left_p.close();
        left.setPath(left_p, globalRegion);

        up_p.addArc(bigCircle, 228, bigSweepAngle);
        up_p.arcTo(smallCircle, 310, smallSweepAngle);
        up_p.close();
        up.setPath(up_p, globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] pts = new float[2];
        pts[0] = event.getRawX();
        pts[1] = event.getRawY();
        mMapMatrix.mapPoints(pts);

        int x = (int) pts[0];
        int y = (int) pts[1];

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedPath(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_UP:
                currentFlag = getTouchedPath(x, y);
                //如果手指按下区域和抬起区域相同且不为空,则为点击事件
                if (currentFlag == touchFlag && currentFlag != -1 && mListener != null) {
                    if (currentFlag == CENTER) {
                        mListener.onCenterClicked();
                    } else if (currentFlag == RIGHT) {
                        mListener.onRightClicked();
                    } else if (currentFlag == DOWN) {
                        mListener.onDownClicked();
                    } else if (currentFlag == LEFT) {
                        mListener.onLeftClicked();
                    } else if (currentFlag == UP) {
                        mListener.onUpClicked();
                    }
                }
                touchFlag = currentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1;
                break;
        }
        invalidate();
        return true;
    }

    //获取当前触摸点在哪个区域
    private int getTouchedPath(int x, int y) {
        if (center.contains(x, y)) {
            return CENTER;
        } else if (right.contains(x, y)) {
            return RIGHT;
        } else if (down.contains(x, y)) {
            return DOWN;
        } else if (left.contains(x, y)) {
            return LEFT;
        } else if (up.contains(x, y)) {
            return UP;
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        //获取测量矩阵(逆矩阵)
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        CanvasAidUtils.set2DAxisLength(500, 500, 700, 500);
        CanvasAidUtils.setLineColor(Color.CYAN);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);

        canvas.drawPath(center_p, mPaint);
        canvas.drawPath(right_p, mPaint);
        canvas.drawPath(down_p, mPaint);
        canvas.drawPath(left_p, mPaint);
        canvas.drawPath(up_p, mPaint);

        //绘制触摸区域颜色
        mPaint.setColor(mTouchedColor);
        if (currentFlag == CENTER) {
            canvas.drawPath(center_p, mPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(right_p, mPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(down_p, mPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(left_p, mPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(up_p, mPaint);
        }
        mPaint.setColor(mDefaultColor);

    }

    //点击事件监听器
    public interface MenuListener {

        void onCenterClicked();

        void onRightClicked();

        void onDownClicked();

        void onLeftClicked();

        void onUpClicked();
    }

}
