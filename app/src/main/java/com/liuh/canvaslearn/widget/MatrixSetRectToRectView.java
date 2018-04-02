package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liuh.canvaslearn.R;

/**
 * Date: 2018/4/2 16:03
 * Description:Matrix类的setRectToRect方法测试
 * <p>
 * mRectMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
 * 简单来说就是将源矩形的内容填充到目标矩形中，然而在大多数的情况下，源矩形和目标矩形的长宽比是不一致的，
 * 如何填充由第三个参数 stf 来确定
 * <p>
 * CENTER	居中，对src等比例缩放，将其居中放置在dst中。
 * START	顶部，对src等比例缩放，将其放置在dst的左上角。
 * END	    底部，对src等比例缩放，将其放置在dst的右下角。
 * FILL	    充满，拉伸src的宽和高，使其完全填充满dst。
 */

public class MatrixSetRectToRectView extends View {

    private int mWidth, mHeight;

    private Bitmap mBitmap;//要绘制的图片

    private Matrix mRectMatrix;//测试setRectToRect的Matrix

    public MatrixSetRectToRectView(Context context) {
        this(context, null);
    }

    public MatrixSetRectToRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixSetRectToRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rect_test);
        mRectMatrix = new Matrix();
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

        RectF src = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF dst = new RectF(0, 0, mWidth, mHeight);

        //核心要点
        mRectMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        //根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mRectMatrix, null);
    }
}
