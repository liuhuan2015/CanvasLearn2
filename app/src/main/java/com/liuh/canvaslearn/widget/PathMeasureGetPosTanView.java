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
 * Date: 2018/3/29 08:52
 * Description:使用PathMeasure的getPosTan(...)制作的一个箭头绕环运动效果
 * <p>
 * boolean getPosTan (float distance, float[] pos, float[] tan)
 * <p>
 * 这个方法是用于得到路径上某一长度的位置以及该位置的正切值
 * <p>
 * 返回值(boolean)	判断获取是否成功	true表示成功，数据会存入 pos 和 tan 中，
 * false 表示失败，pos 和 tan 不会改变
 * distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
 * pos	该点的坐标值	当前点在画布上的位置，有两个数值，分别为x，y坐标。
 * tan	该点的正切值	当前点在曲线上的方向，使用 Math.atan2(tan[1], tan[0]) 获取到正切角的弧度值。
 * <p>
 * 这里关于Matrix的部分有点看不太懂呀
 * <p>
 * Math.atan2(double y, double x)是根据正切数值计算出角度大小,返回一个-PI到PI之间的数值,单位是弧度
 * 注意:它的第一个参数是y轴坐标,第二个参数是x轴坐标
 * <p>
 * 对于图片的绘制使用了Canvas类的 drawBitmap(Bitmap bitmap,Matrix matrix,Paint paint)方法
 */

public class PathMeasureGetPosTanView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private float currentValue = 0;//用于记录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;//当前点的实际位置

    private float[] tan;//当前点的tan值,用于计算图片所需旋转的角度

    private Bitmap mBitmap;//箭头图片

    private Matrix mMatrix;//矩阵,用于对图片进行一些操作


    public PathMeasureGetPosTanView(Context context) {
        this(context, null);
    }

    public PathMeasureGetPosTanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureGetPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        pos = new float[2];
        tan = new float[2];

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 2;

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

        path.addCircle(0, 0, 200, Path.Direction.CW);//添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);

        currentValue += 0.005;//计算当前位置在总长度上的比例

        if (currentValue >= 1) {
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);//获取当前的位置以及趋势

        mMatrix.reset();

        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);//计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);//旋转图片

        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);//将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        invalidate();
    }
}
