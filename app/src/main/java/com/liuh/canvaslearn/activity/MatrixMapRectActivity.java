package com.liuh.canvaslearn.activity;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liuh.canvaslearn.R;

/**
 * 测量矩形变换后位置
 * boolean mapRect (RectF rect)
 * 测量rect并将测量结果放入rect中，返回值是判断矩形经过变换后是否仍为矩形。
 * <p>
 * boolean mapRect (RectF dst, RectF src)
 * 测量src并将测量结果放入dst中，返回值是判断矩形经过变换后是否仍为矩形,和上面的方法没有太大区别
 */
public class MatrixMapRectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_map_rect);

        RectF rectF = new RectF(400, 400, 1000, 800);

        //构造一个Matrix
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        matrix.postSkew(1, 0);

        Log.e("--------", "mapRect:" + rectF.toString());
        //进行测量，返回值表示变换后是否仍为矩形
        boolean isRectF = matrix.mapRect(rectF);

        Log.e("--------", "mapRect:" + rectF.toString());
        Log.e("--------", "isRectF:" + isRectF);//由于使用了错切，所以此处会返回false

    }
}
