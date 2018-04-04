package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

/**
 * 矩阵相关的函数属于一种非常靠近底层的东西，大部分开发者很少直接接触这些东西
 * invert(...),isAffine(),isIdentity()
 */
public class MatrixAboutMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_about_method);

//        matrixInvertTest();

//        matrixIsAffineTest();

        matrixIsIdentityTest();

    }

    /**
     * 判断是否为单位矩阵
     */
    private void matrixIsIdentityTest() {
        Matrix matrix = new Matrix();
        Log.e("---------", "isIdentity:" + matrix.isIdentity());
        matrix.postTranslate(200, 0);
        Log.e("---------", "isIdentity:" + matrix.isIdentity());
    }

    /**
     * 判断矩阵是否是仿射矩阵
     * 判断是否是仿射矩阵最重要的一点就是，直线是否仍为直线，简单想一下就知道，
     * 不论平移，旋转，错切，缩放，直线变换后最终仍为直线。
     * 即迄今为止我们使用的所有变换都是仿射变换，那变换出来的矩阵自然是仿射矩阵
     */
    private void matrixIsAffineTest() {
        Matrix matrix = new Matrix();
        Log.e("---------", "isAffine:" + matrix.isAffine());

        matrix.postTranslate(200, 0);
        matrix.postScale(0.5f, 1);
        matrix.postSkew(0, 1);
        matrix.postRotate(56);
        Log.e("---------", "isAffine:" + matrix.isAffine());
    }

    /**
     * 求矩阵的逆矩阵，简而言之就是计算与之前相反的矩阵，如果之前是平移200px，
     * 则求的矩阵为反向平移200px，如果之前是缩小到0.5f，则结果是放大到2倍
     */
    private void matrixInvertTest() {
        Matrix matrix = new Matrix();
        Matrix invert = new Matrix();
        matrix.setTranslate(200, 500);
        Log.e("---------", "before--matrix:" + matrix.toShortString());

        boolean result = matrix.invert(invert);

        Log.e("---------", "after--result:" + result);
        Log.e("---------", "after--matrix:" + matrix.toShortString());
        Log.e("---------", "after--invert:" + invert.toShortString());
    }
}
