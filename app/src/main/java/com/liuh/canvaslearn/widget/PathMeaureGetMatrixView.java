package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liuh.canvaslearn.R;

/**
 * Created by huan on 2018/3/29.
 * <p>
 * 这个是使用PathMeasure的getMatrix(...)制作的箭头绕环运动效果，和前面用getPosTan(...)效果一样
 * <p>
 * boolean getMatrix (float distance, Matrix matrix, int flags)
 * <p>
 * 返回值(boolean)	判断获取是否成功	true表示成功，数据会存入matrix中，false 失败，matrix内容不会改变
 * distance	        距离 Path 起点的长度	                    取值范围: 0 <= distance <= getLength
 * matrix	        根据 falgs 封装好的matrix	                会根据 flags 的设置而存入不同的内容
 * flags	        规定哪些内容会存入到matrix中	            可选择POSITION_MATRIX_FLAG(位置),ANGENT_MATRIX_FLAG(正切)
 * <p>
 * 1.对 matrix 的操作必须要在 getMatrix 之后进行，否则会被 getMatrix 重置而导致无效。
 * 2.矩阵对旋转角度默认为图片的左上角，我们此处需要使用 preTranslate 调整为图片中心。
 * 3.pre(矩阵前乘) 与 post(矩阵后乘) 的区别，此处请等待后续的文章或者自行搜索。
 */

public class PathMeaureGetMatrixView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private Matrix mMatrix;

    private float currentValue;

    private Bitmap mBitmap;

    public PathMeaureGetMatrixView(Context context) {
        this(context, null);
    }

    public PathMeaureGetMatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeaureGetMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;//缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
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

        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();

        path.addCircle(0, 0, 200, Path.Direction.CW);

        currentValue += 0.005;

        if (currentValue >= 1.0) {
            currentValue = 0;
        }

        PathMeasure pathMeasure = new PathMeasure(path, false);

        //获取当前位置的坐标和趋势的矩阵
        pathMeasure.getMatrix(currentValue * pathMeasure.getLength(), mMatrix,
                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);

        //将图片绘制中心调整到与当前点重合(注意：此处是前乘pre)
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();//重绘界面

    }
}
