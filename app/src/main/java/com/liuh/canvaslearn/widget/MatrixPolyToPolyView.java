package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liuh.canvaslearn.R;

/**
 * Date: 2018/4/2 11:03
 * Description:Matrix的setPolyToPoly(...)的简单使用
 * 本例是通过setPolyToPoly(...)把四个坐标点变成另外四个坐标点,
 * 然后使用 canvas.drawBitmap(mBitmap, mPolyMatrix, null)把这个变化加到了mBitmap上;
 */

public class MatrixPolyToPolyView extends View {

    private Bitmap mBitmap;

    private Matrix mPolyMatrix;

    public MatrixPolyToPolyView(Context context) {
        this(context, null);
    }

    public MatrixPolyToPolyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixPolyToPolyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.poly_test);
        mPolyMatrix = new Matrix();

        float[] src = {0, 0,//左上
                mBitmap.getWidth(), 0,//右上
                mBitmap.getWidth(), mBitmap.getHeight(),//右下
                0, mBitmap.getHeight()};//左下

        float[] dst = {0, 0,//左上
                mBitmap.getWidth(), 400,//右上
                mBitmap.getWidth(), mBitmap.getHeight() - 200,//右下
                0, mBitmap.getHeight()};//左下

        //核心要点(src.length >> 1 为位移运算,相当于除以2)
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);

        //为了更好地显示,对图片进行了等比缩放和平移(图片本身有点大)
        mPolyMatrix.postScale(0.26f, 0.26f);
        mPolyMatrix.postTranslate(0, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
    }
}
