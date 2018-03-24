package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by huan on 2018/3/24.
 * Path练习：雷达图，参考文章https://blog.csdn.net/crazy__chen/article/details/50163693
 */

public class PathUseDetailRadarView extends View {

    private int mWidth, mHeight;

    private int count = 6;//数据个数

    private float angle = (float) (Math.PI * 2 / count);//各个分区对应的弧度

    private float radius;//网格最大半径

    private int centerX, centerY;//中心点

    private String[] titles = {"输出", "金钱", "发育", "打野", "生存", "团战",};

    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};//各维度分值，后两个数值是没有用到的，因为这里的count是6

    private float maxValue = 100;//数据最大值

    private Paint mainPaint;//雷达区画笔

    private Paint textPaint;//文本画笔

    private Paint valuePaint;//数据区画笔


    public PathUseDetailRadarView(Context context) {
        this(context, null);
    }

    public PathUseDetailRadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathUseDetailRadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeWidth(1);
        mainPaint.setColor(Color.BLACK);

        textPaint = new Paint();
        textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                getResources().getDisplayMetrics()));
        textPaint.setColor(Color.BLUE);

        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        radius = Math.min(mWidth, mHeight) / 2 * 0.7f;
        //中心坐标
        centerX = mWidth / 2;
        centerY = mHeight / 2;
//        postInvalidate();
//        super.onSizeChanged(w, h, oldw, oldh);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * @param canvas 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();

        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = centerX + (float) (radius * Math.cos(angle * i));
            float y = centerY + (float) (radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     * 当文本在坐标系的左边的时候，不希望文本和蜘蛛网交叉
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));

            if (angle * i >= 0 && angle * i < Math.PI / 2) {
                //第一象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= Math.PI / 2 && angle * i <= Math.PI) {
                //第二象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i > Math.PI && angle * i <= Math.PI / 2 * 3) {
                //第三象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < Math.PI * 2) {
                //第四象限
                canvas.drawText(titles[i], x, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {

        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;

            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);

            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);

        //绘制填充区域
        valuePaint.setAlpha(127);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);

    }

}
