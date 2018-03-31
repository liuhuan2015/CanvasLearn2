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
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Date: 2018/3/30 11:26
 * Description:照着原代码写了一遍，写出来后觉得效果还是挺赞的，然后发现自己对ValueAnimator的使用好像是空白...
 * <p>
 * 思路：
 * <p>
 * 状态	    概述
 * 初始状态	初始状态，没有任何动效，只显示一个搜索标志 🔍
 * 准备搜索	放大镜图标逐渐变化为一个点
 * 正在搜索	围绕这一个圆环运动，并且线段长度会周期性变化
 * 准备结束	从一个点逐渐变化成为放大镜图标
 * <p>
 * 整个动画效果用了两个Path，一个是中间放大镜，一个是外侧的圆环，其中Path的走向要把握好
 * <p>
 * Path上面的点是使用的PathMeasure进行的测量，无需计算
 * <p>
 * 动画效果使用了ValueAnimator，它可以将一段时间映射到一段数值上，随着时间的变化不断的更新数值，
 * 并且可以使用插值器来控制数值变化规律
 * <p>
 * 具体的绘制是根据当前状态以及从ValueAnimator获得的数值来截取Path中合适的部分绘制出来
 */

public class PathMeasureSearchView extends View {

    private Paint mPaint;

    private int mWidth, mHeight;

    private Path path_search, path_circle;

    private PathMeasure pathMeasure;

    //这个视图拥有的状态
    public static enum State {
        NONE,
        STARTING,//准备搜索，放大镜图标逐渐变化为一个点
        SEARCHING,//正在搜索，围绕着一个圆环运动，并且线段长度会周期性变化
        ENDING//结束搜索，从一个点逐渐变化成为放大镜图标
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

    public PathMeasureSearchView(Context context) {
        this(context, null);
    }

    public PathMeasureSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initPath();

        initListener();

        initHandler();

        initAnimator();

        //进入开始动画
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
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

        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }

    //第一个动画结束后，发送一个消息到这里，走case STARTING，在其中开启了搜索动画；
    //搜索动画结束后，也发送了一个消息到这里，走 case SEARCHING，在其中开启结束动画
    private void initHandler() {
        mAnimatorHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case STARTING:
                        Log.e("----------", "STARTING...准备开始");
                        isOver = false;
                        mCurrentState = State.SEARCHING;
                        mStartingAnimator.removeAllListeners();
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING:
                        if (!isOver) {//如果搜索未结束，则继续执行搜索动画
                            mSearchingAnimator.start();
                            count++;
                            if (count > 2) {//count大于2则进入结束状态
                                isOver = true;
                            }
                        } else {//如果搜索已经结束，则进入结束动画
                            mCurrentState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        //从结束动画转变为无状态
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };
    }

    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
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
        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            case STARTING:
                pathMeasure.setPath(path_search, false);
                Path dst = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * mAnimatorValue,
                        pathMeasure.getLength(), dst, true);//true，保证截取出来的片段不会发生形变
                canvas.drawPath(dst, mPaint);
                break;
            case SEARCHING:
                pathMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = pathMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                pathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case ENDING:
                pathMeasure.setPath(path_search, true);
                Path dst3 = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * mAnimatorValue, pathMeasure.getLength(),
                        dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }
}
