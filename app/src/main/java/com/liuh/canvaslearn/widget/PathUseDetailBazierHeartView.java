package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by huan on 2018/3/25.
 * Path的贝塞尔曲线：心形图案
 * <p>
 * 实现思路：
 * 我们最终的需要的效果是将一个圆转变成一个心形，通过分析可知，圆可以由四段三阶贝塞尔曲线组合而成。
 * 而心形也可以由四段的三阶的贝塞尔曲线组成
 * <p>
 * 两者的差别仅仅在于数据点和控制点位置不同，因此只需要调整数据点和控制点的位置，就能将圆形变为心形。
 * <p>
 * 绘制贝塞尔曲线的方法cubicTo(...)，其中的参数是两个控制点的坐标和一个最终数据点的坐标
 * <p>
 * 感觉这个View的代码是照着抄了一遍。
 * <p>
 * 作者的话：关于贝塞尔曲线最重要的是理解贝塞尔曲线的生成方式，只有理解了贝塞尔曲线的生成方式，才能更好的运用贝塞尔曲线。
 */

public class PathUseDetailBazierHeartView extends View {

    private static final float C = 0.551915024494f;//一个常量，用来计算绘制圆形贝塞尔曲线控制点的

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);
    private float mCircleRadius = 200;              //圆的半径
    private float mDifference = mCircleRadius * C;  //圆的控制点与数据点的差值

    private float[] mData = new float[8];//顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];//顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;//变化总时长
    private float mCurrent = 0;//当前已进行时长
    private float mCount = 100;//将总时长划分为多少份
    private float mPiece = mDuration / mCount;//每一份的时长

    public PathUseDetailBazierHeartView(Context context) {
        this(context, null);
    }

    public PathUseDetailBazierHeartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailBazierHeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18,
                getResources().getDisplayMetrics()));

        //初始化数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        //初始化控制点
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCoordinateSystem(canvas);//绘制坐标系

        canvas.translate(mCenterX, mCenterY);//将坐标系移动到画布中央

        canvas.scale(1, -1);//翻转y轴

        drawAuxiliaryLine(canvas);//绘制辅助线

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);

        canvas.drawPath(path, mPaint);
        mCurrent += mPiece;
        if (mCurrent < mDuration) {
            mData[1] -= 120 / mCount;
            mCtrl[7] += 80 / mCount;
            mCtrl[9] += 80 / mCount;

            mCtrl[4] -= 20 / mCount;
            mCtrl[10] += 20 / mCount;

            postInvalidateDelayed((long) mPiece);
        }

    }

    /**
     * 绘制辅助线
     *
     * @param canvas
     */
    private void drawAuxiliaryLine(Canvas canvas) {
        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        for (int i = 0; i < 8; i += 2) {
            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < 16; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        //绘制辅助线
        mPaint.setStrokeWidth(4);

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }

        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);
    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();

        canvas.translate(mCenterX, mCenterY);//把坐标系移动到画布中央
        canvas.scale(1, -1);//翻转y轴

        Paint fuzhuPaint = new Paint();
        fuzhuPaint.setColor(Color.RED);
        fuzhuPaint.setStrokeWidth(5);
        fuzhuPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -2000, 0, 2000, fuzhuPaint);
        canvas.drawLine(-2000, 0, 2000, 0, fuzhuPaint);

        canvas.restore();
    }
}
