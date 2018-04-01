package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

import java.util.Arrays;

/**
 * mapVectors  测量向量
 * <p>
 * void mapVectors (float[] vecs)
 * <p>
 * void mapVectors (float[] dst, float[] src)
 * <p>
 * void mapVectors (float[] dst, int dstIndex, float[] src, int srcIndex, int vectorCount)
 * <p>
 * mapVectors 与 mapPoints 基本上是相同的，可以直接参照mapPoints使用方法。
 * 而两者唯一的区别就是mapVectors不会受到位移的影响，这符合向量的定律。
 */
public class MatrixMapVectorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_map_vectors);

        float[] src = new float[]{1000, 800};
        float[] dst = new float[2];

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        matrix.postTranslate(100, 100);

        //计算向量，不受位移影响
        matrix.mapVectors(dst, src);
        Log.e("--------", "dst:" + Arrays.toString(dst));

        //计算点
        matrix.mapPoints(dst, src);
        Log.e("--------", "dst:" + Arrays.toString(dst));
    }
}
