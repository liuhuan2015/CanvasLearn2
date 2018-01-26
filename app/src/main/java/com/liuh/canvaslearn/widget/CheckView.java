package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liuh.canvaslearn.R;

/**
 * Date: 2018/1/26 10:55
 * Description:使用drawBitmap的第三种方法
 * public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
 * public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)制作的一个效果
 * <p>
 * 把同一个动画效果的所有资源图片整理到一张图片上，会大大的减少资源文件数量，方便管理，妈妈再也不怕我找不到资源文件了，同时也节省了图片文件头、文件结束块以及调色板等占用的空间
 * 作者原话:由于是示例代码，做的很粗糙，仅作为学习示例，不建议在任何实际项目中使用
 */

public class CheckView extends View {

    private static final int ANIM_NULL = 0;//动画状态-没有
    private static final int ANIM_CHECK = 1;//动画状态-开始
    private static final int ANIM_UNCHECK = 2;//动画状态-结束

    private Context mContext;
    private int mWidth, mHeight;
    private Handler mHandler;

    private Paint mPaint;
    private Bitmap mBitmap;

    private int animCurrentPage = -1;//当前页码
    private int animMaxPage = 13;//总页数
    private int animDuration = 500;//动画时长
    private int animState = ANIM_NULL;//动画状态

    private boolean isCheck = false;//是否是选中状态

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();

                    if (animState == ANIM_NULL)
                        return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animState--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e("-----------", "Count=" + animCurrentPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
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
        canvas.drawCircle(0, 0, 240, mPaint);

        //得出图像边长
        int sideLength = mBitmap.getHeight();

        //得到图像选区和实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-200, -200, 200, 200);

        //绘制
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck) return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck)) return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     *
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0) return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形的颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }

    public boolean isCheck() {
        return isCheck;
    }
}
