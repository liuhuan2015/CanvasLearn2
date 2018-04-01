package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

/**
 * float mapRadius (float radius)
 * 测量半径，由于圆可能会因为画布变换变成椭圆，所以此处测量的是平均半径。
 */
public class MatrixMapRadiusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_map_radius);

        float radius = 100;
        float result = 0;

        //构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        Log.e("--------", "mapRadius:" + radius);
        result = matrix.mapRadius(radius);
        Log.e("--------", "mapRadius:" + result);

    }
}
