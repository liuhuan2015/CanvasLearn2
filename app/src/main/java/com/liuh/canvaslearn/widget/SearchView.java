package com.liuh.canvaslearn.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Date: 2018/3/30 11:26
 * Description:
 */

public class SearchView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private Path path_search, path_circle;

    private PathMeasure pathMeasure;

    //这个视图拥有的状态
    public static enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    //视图当前的状态
    private State mCurrentState = State.NONE;
    //默认的动效周期
    private int defaultDuration = 2000;

    //控制各个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    //动画数值(用于控制动画状态,因为同一时间内只允许有一种状态出现,具体数值处理取决于当前状态)
    private float mAnimatorValue = 0;

    //动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    //用于控制动画状态转换
    private Handler mAnimatorHandler;

    //搜索是否已经结束
    private boolean isOver = false;

    private int count = 0;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initPath();

        initListener();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }

    private void initPath() {
        path_search = new Path();
        path_circle = new Path();

        pathMeasure = new PathMeasure();

        //注意:不要360度,否则内部会自动优化,测量不能取到需要的值
        RectF oval1 = new RectF(-50, -50, 50, 50);//放大镜圆环
        path_search.addArc(oval1, 45, 359.9f);

        RectF oval2 = new RectF(-100, -100, 100, 100);//外部圆环
        path_circle.addArc(oval2, 45, -359.9f);

        float[] pos = new float[2];//放大镜把手的位置

        pathMeasure.setPath(path_circle, false);
        pathMeasure.getPosTan(0, pos, null);

        path_search.lineTo(pos[0], pos[1]);

        Log.e("----------", "pos=" + pos[0] + " , " + pos[1]);
    }

    private void initListener() {


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
        canvas.drawPath(path_search, mPaint);
        canvas.drawPath(path_circle, mPaint);
    }
}
