package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

import java.util.Arrays;

/**
 * void mapPoints (float[] pts)
 * 方法仅有一个参数，pts数组作为参数传递原始数值，计算结果仍存放在pts中。
 * <p>
 * void mapPoints (float[] dst, float[] src) ，
 * src作为参数传递原始数值，计算结果存放在dst中，src不变。
 * 如果原始数据需要保留则一般使用这种方法
 * <p>
 * void mapPoints (float[] dst, int dstIndex,float[] src, int srcIndex, int pointCount)
 * 可以指定只计算一部分数值。
 * 参数	         摘要
 * dst	         目标数据
 * dstIndex	     目标数据存储位置起始下标
 * src	         源数据
 * srcIndex	     源数据存储位置起始下标
 * pointCount	 计算的点个数
 */
public class MatrixMapPointsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_map_points);

        //mapPointsWithOneArguTest();

//        mapPointsWithTwoArguTest();

        mapPointsWithFiveArguTest();
    }


    /**
     * mapPoints (float[] pts)测试
     */
    private void mapPointsWithOneArguTest() {
        //初始数据为三个点
        float[] pts = new float[]{0, 0, 80, 100, 400, 300};
        //构造一个Matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        //输出pts计算之前的数据
        Log.e("--------", "before:" + Arrays.toString(pts));
        //调用Matrix的mapPoints方法进行数值计算
        matrix.mapPoints(pts);
        //输出pts计算之后的数据
        Log.e("--------", "after:" + Arrays.toString(pts));
    }

    /**
     * mapPoints (float[] dst, float[] src)测试
     */
    private void mapPointsWithTwoArguTest() {
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        Log.e("--------", "before: src=" + Arrays.toString(src));
        Log.e("--------", "before: dst=" + Arrays.toString(dst));

        //调用mapPoints方法进行数值计算
        matrix.mapPoints(dst, src);

        Log.e("--------", "after: src=" + Arrays.toString(src));
        Log.e("--------", "after: dst=" + Arrays.toString(dst));
    }

    /**
     * mapPoints (float[] dst, int dstIndex,float[] src, int srcIndex, int pointCount)测试
     */
    private void mapPointsWithFiveArguTest() {
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        Log.e("--------", "before: src=" + Arrays.toString(src));
        Log.e("--------", "before: dst=" + Arrays.toString(dst));

        //调用Matrix的mapPoints方法进行数值计算（最后一个2表示两个点，即四个数值，并非两个数值）
        matrix.mapPoints(dst, 0, src, 2, 2);

        Log.e("--------", "after: src=" + Arrays.toString(src));
        Log.e("--------", "after: dst=" + Arrays.toString(dst));
    }
}
