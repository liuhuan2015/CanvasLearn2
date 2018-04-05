package com.liuh.canvaslearn.animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Date: 2018/4/4 11:07
 * Description:官方示例ApiDemo里面有一个Rotate3dAnimation，有不少博文都是根据它修改的效果
 */

public class Rotate3dAnimation extends Animation {

    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;

    private Camera mCamera;
    float scale = 1;//像素密度

    /**
     * 创建一个绕y轴旋转的3D动画效果，旋转过程中具有深度调节，可以指定旋转中心
     *
     * @param context      上下文，为获取像素密度准备
     * @param mFromDegrees 起始时角度
     * @param mToDegrees   结束时角度
     * @param mCenterX     旋转中心x坐标
     * @param mCenterY     旋转中心y坐标
     * @param mDepthZ      最远到达的z轴坐标
     * @param mReverse     true表示从0到depthz，false相反
     */
    public Rotate3dAnimation(Context context, float mFromDegrees, float mToDegrees,
                             float mCenterX, float mCenterY, float mDepthZ, boolean mReverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        this.mReverse = mReverse;

        //获取手机像素密度(即dp和px的比例)
        scale = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    /**
     * applyTransformation方法是动画具体的实现，系统会以一个比较高的频率来调用这个方法，一般情况下60FPS，
     * 是一个非常流畅的画面了，也就是16ms
     *
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        final float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        camera.save();

        //深度调节，在Z轴方向上进行位移
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }

        //绕Y轴旋转
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        //修正失真，主要修改MPERSP_0和MPERSP_1
        //不修正的话，图片会因为形变失真，而且在中间一段因为形变过大导致图片无法显示，
        //当然了，单个手机失真，你可以用depthZ忽悠过去，当 depthZ 设置的数值比较大大时候，
        //图像在翻转同时会远离摄像头，距离比较远，失真就不会显得很严重，
        //但这仍掩盖不了在不同手机上显示效果不同
        float[] mValues = new float[9];
        matrix.getValues(mValues);//获取数值
        mValues[6] = mValues[6] / scale;//数值修正
        mValues[7] = mValues[7] / scale;//数值修正
        matrix.setValues(mValues);//重新赋值

        //调节中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
