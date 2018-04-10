package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;

/**
 * Date: 2018/4/10 09:53
 * Description:
 * 画布移动后在手指按下位置绘制一个圆，可以看到，直接拿手指触摸位置的坐标来绘制会导致绘制位置不正确，
 * 两者坐标是相同的，但是由于坐标系不同，导致实际显示位置不同。
 * <p>
 * 知识回顾:
 * event.getX():触摸点相对于其所在组件坐标系的坐标
 * event.getY()
 * <p>
 * event.getRawX():触摸点相对于屏幕默认坐标系的坐标
 * event.getRawY
 * <p>
 * 为了解决手指触摸位置和界面绘制位置不一致的问题,使用了Matrix.
 * 解决:
 * 1,使用全局坐标系
 * down_X = event.getRawX();
 * down_Y = event.getRawY();
 * 2,使用逆矩阵(invert方法求逆矩阵),坐标点位置转换(mapPoints方法)
 * //获得当前矩阵的逆矩阵
 * Matrix invertMatrix = new Matrix();
 * canvas.getMatrix().invert(invertMatrix);
 * //使用mapPoints将触摸位置转换为画布坐标
 * invertMatrix.mapPoints(pts);
 */

public class CanvasConvertTouchTest extends View {

    private int mWidth, mHeight;
    private Paint mPaint;

    float down_X = -1;
    float down_Y = -1;

    public CanvasConvertTouchTest(Context context) {
        this(context, null);
    }

    public CanvasConvertTouchTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasConvertTouchTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
//                down_X = event.getX();
//                down_Y = event.getY();
                down_X = event.getRawX();
                down_Y = event.getRawY();
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                down_X = -1;
                down_Y = -1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float[] pts = {down_X, down_Y};
//        float x = down_X;
//        float y = down_Y;
        //绘制触摸坐标系
        drawTouchCoordinateSpace(canvas);

        //画布坐标系原点位置移动到控件中心位置
        canvas.translate(mWidth / 2, mHeight / 2);

        //绘制平移后的坐标系,红色
        drawTranslateCoordinateSpace(canvas);

        if (pts[0] == -1 && pts[1] == -1) return;//如果没有就返回

        //获得当前矩阵的逆矩阵
        Matrix invertMatrix = new Matrix();
        canvas.getMatrix().invert(invertMatrix);
        //使用mapPoints将触摸位置转换为画布坐标
        invertMatrix.mapPoints(pts);

        canvas.drawCircle(pts[0], pts[1], 20, mPaint);//在触摸位置绘制一个小圆
    }

    private void drawTouchCoordinateSpace(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 10);
        CanvasAidUtils.set2DAxisLength(1000, 0, 1400, 0);
        CanvasAidUtils.setLineColor(Color.GRAY);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
        canvas.restore();
    }

    private void drawTranslateCoordinateSpace(Canvas canvas) {
        CanvasAidUtils.set2DAxisLength(500, 500, 700, 700);
        CanvasAidUtils.setLineColor(Color.RED);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
    }
}
