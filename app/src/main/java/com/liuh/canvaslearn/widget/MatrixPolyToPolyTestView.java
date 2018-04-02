package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;
import com.liuh.canvaslearn.R;

/**
 * Date: 2018/4/2 14:15
 * Description:测试Matrix的setPolyToPoly(...)方法中的pointCount数值分别为0,1,2,3,4时效果
 * <p>
 * pointCount为0
 * pointCount为0和reset是等价的，而不是保持matrix不变.
 * <p>
 * pointCount为1和translate是等价的.
 * <p>
 * 当pointCount为2的时候，可以做缩放、平移和旋转.
 * <p>
 * 当pointCount为3的时候，可以做缩放、平移、旋转和错切.
 * <p>
 * 当pointCount为4的时候，你可以将图像拉伸为任意四边形.
 */

public class MatrixPolyToPolyTestView extends View {

    private int testPoint = 0;

    private int triggerRadius = 180;//触发半径为180px

    private Bitmap mBitmap;//要绘制的图片

    private Matrix mPolyMatrix;

    private float[] src = new float[8];
    private float[] dst = new float[8];

    private Paint pointPaint;


    public MatrixPolyToPolyTestView(Context context) {
        this(context, null);
    }

    public MatrixPolyToPolyTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixPolyToPolyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.poly_test2);
        float[] temp = {0, 0,
                mBitmap.getWidth(), 0,
                mBitmap.getWidth(), mBitmap.getHeight(),
                0, mBitmap.getHeight()};
        src = temp.clone();
        dst = temp.clone();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(0xffd19156);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPolyMatrix = new Matrix();
        mPolyMatrix.setPolyToPoly(src, 0, src, 0, 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX();
                float tempY = event.getY();

                //根据触控点的位置改变dst
                for (int i = 0; i < testPoint * 2; i += 2) {
                    if (Math.abs(tempX - dst[i]) <= triggerRadius && Math.abs(tempY - dst[i + 1]) <= triggerRadius) {
                        dst[i] = tempX;
                        dst[i + 1] = tempY;
                        break;//防止两个点的位置重合
                    }
                }
                resetPolyMatrix(testPoint);
                invalidate();
                break;
        }
        return true;
    }

    public void resetPolyMatrix(int pointCount) {
        mPolyMatrix.reset();
        //核心要点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100, 100);

        //绘制坐标系
        CanvasAidUtils.set2DAxisLength(900, 0, 1000, 0);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);

        //根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);

        float[] dst = new float[8];

        mPolyMatrix.mapPoints(dst, src);

        //绘制触控点
        for (int i = 0; i < testPoint * 2; i += 2) {
            canvas.drawPoint(dst[i], dst[i + 1], pointPaint);
        }
    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint > 4 || testPoint < 0 ? 4 : testPoint;
        dst = src.clone();
        resetPolyMatrix(this.testPoint);
        invalidate();
    }
}
