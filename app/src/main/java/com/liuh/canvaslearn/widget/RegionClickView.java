package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by huan on 2018/4/9.
 * 思路：
 * 创建了个 Path 并在其中添加圆形，之后将 Path 设置到 Region 中，
 * 当手指在屏幕上按下的时候判断手指位置是否在 Region 区域内
 */

public class RegionClickView extends View {

    private int mWidth, mHeight;
    private Paint mPaint;

    Region circleRegion;
    Path circlePath;

    public RegionClickView(Context context) {
        this(context, null);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(0xFF4E5268);

        circlePath = new Path();
        circleRegion = new Region();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mWidth = w;
//        mHeight = h;
        //在屏幕中间添加一个圆
        circlePath.addCircle(w / 2, h / 2, 300, Path.Direction.CW);
        //将裁剪边界设置为视图大小
        Region globalRegion = new Region(-w, -h, w, h);
        //将Path添加到Region中
        circleRegion.setPath(circlePath, globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                //点击区域判断
                if (circleRegion.contains(x, y)) {
                    Toast.makeText(this.getContext(), "圆被点击了", Toast.LENGTH_SHORT).show();
                }

                break;
        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将全局变量转化为局部变量，方便GC回收canvas
        Path circle = circlePath;
        //绘制圆
        canvas.drawPath(circle, mPaint);
    }
}
