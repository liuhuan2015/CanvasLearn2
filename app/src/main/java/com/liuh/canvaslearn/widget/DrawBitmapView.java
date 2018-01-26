package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liuh.canvaslearn.R;

/**
 * Date: 2018/1/26 09:48
 * Description:绘制bitmap
 * <p>
 * 在得到一个Bitmap后,我们就需要将其绘制到画布Canvas上,有三种绘制方法,如下:
 * <p>
 * 绘制方法:
 * <p>
 * 第一种:public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)
 * 后两个参数(matrix, paint)是在绘制的时候对图片进行一些改变，如果只是需要将图片内容绘制出来只需要如下操作就可以了
 * <p>
 * 第二种:public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
 * 此处指定的是与坐标原点的距离，并非是与屏幕顶部和左侧的距离, 虽然默认状态下两者是重合的，但是也请注意分别两者的不同
 * <p>
 * 第三种:public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
 * public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)
 * 可以绘制图片的一部分到画布上
 * src指定了图片绘制部分的区域,本例中为图片的1/4区域;dst指定了绘制在屏幕上的绘制,图片宽高会根据指定的区域自动进行缩放
 */

public class DrawBitmapView extends View {

    private Bitmap mBitmap;

    private int mWidth, mHeight;


    public DrawBitmapView(Context context) {
        this(context, null);
    }

    public DrawBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
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
        //第一种方式
        //后两个参数(matrix, paint)是在绘制的时候对图片进行一些改变，如果只是需要将图片内容绘制出来只需要如下操作就可以了
//        canvas.drawBitmap(mBitmap, new Matrix(), new Paint());

        //第二种方式
        //此处指定的是与坐标原点的距离，并非是与屏幕顶部和左侧的距离, 虽然默认状态下两者是重合的，但是也请注意分别两者的不同
//        canvas.drawBitmap(mBitmap, 200, 500, new Paint());

        //第三种方式
        //可以绘制图片的一部分到画布上
        //src指定了图片绘制部分的区域,本例中为图片的1/4区域;dst指定了绘制在屏幕上的绘制,图片宽高会根据指定的区域自动进行缩放
        //将画布坐标系移动到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);
        //指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, 200, 400);
        //绘制图片
        canvas.drawBitmap(mBitmap, src, dst, null);
    }
}
