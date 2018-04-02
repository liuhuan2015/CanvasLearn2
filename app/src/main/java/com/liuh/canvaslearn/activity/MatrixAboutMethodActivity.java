package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

/**
 * invert(...)
 * 求矩阵的逆矩阵，简而言之就是计算与之前相反的矩阵，如果之前是平移200px，则求的矩阵为反向平移200px，如果之前是缩小到0.5f，则结果是放大到2倍。
 */
public class MatrixAboutMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_about_method);

        matrixInvertTest();
    }

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
