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
 * Date: 2018/3/27 08:52
 * Description:Path使用中的布尔操作.
 * 布尔操作到Api19才有
 * Path的布尔运算有五种逻辑:
 * <p>
 * DIFFERENCE  差集	Path1中减去Path2后剩下的部分
 * <p>
 * REVERSE_DIFFERENCE	差集	 Path2中减去Path1后剩下的部分
 * <p>
 * INTERSECT	交集	Path1与Path2相交的部分
 * <p>
 * UNION	并集	包含全部Path1和Path2
 * <p>
 * XOR	异或	包含Path1与Path2但不包括两者相交的部分
 * <p>
 * 对 path1 和 path2 执行布尔运算，运算方式由第二个参数指定，运算结果存入到path1中。
 * path1.op(path2, Path.Op.DIFFERENCE);
 * 对 path1 和 path2 执行布尔运算，运算方式由第三个参数指定，运算结果存入到path3中。
 * path3.op(path1, path2, Path.Op.DIFFERENCE)
 */

public class PathUseDetailBooleanOperation extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    public PathUseDetailBooleanOperation(Context context) {
        this(context, null);
    }

    public PathUseDetailBooleanOperation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailBooleanOperation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

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

        canvas.translate(mWidth / 2, mHeight / 2);//把坐标系原点位置移动到屏幕中央

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path2, Path.Op.DIFFERENCE);//差集
        path1.op(path3, Path.Op.UNION);//并集
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);
    }
}
