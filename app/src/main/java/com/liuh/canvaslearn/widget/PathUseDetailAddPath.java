package com.liuh.canvaslearn.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huan on 2018/3/20.
 * Path的addPath(...)
 * <p>
 * 第二类(Path)
 * path
 * public void addPath (Path src)
 * public void addPath (Path src, float dx, float dy)
 * public void addPath (Path src, Matrix matrix)
 * <p>
 * 这个相对比较简单，也很容易理解，就是将两个Path合并成为一个。
 * <p>
 * 第三个方法是将src添加到当前path之前先使用Matrix进行变换。
 * <p>
 * 第二个方法比第一个方法多出来的两个参数是将src进行了位移之后再添加进当前path中。
 */

public class PathUseDetailAddPath extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    Path path = new Path();
    Path src = new Path();

    public PathUseDetailAddPath(Context context) {
        this(context, null);
    }

    public PathUseDetailAddPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailAddPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
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
        canvas.scale(1, -1);//反转y轴坐标（scale是缩放的意思）

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        canvas.drawPath(path, mPaint);

    }
}
