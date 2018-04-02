package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by huan on 2018/4/2.
 * Matrix最根本的作用就是坐标映射，将View的相对坐标映射为屏幕的绝对坐标
 * 我们在onDraw函数的canvas中获取到到Matrix并不是单位矩阵，结合这两点，聪明的你肯定想到了我们可以从canvas的Matrix入手取得View在屏幕上的绝对位置.
 * <p>
 * 使用getLocationOnScreen同样可以获取View在屏幕的位置
 * <p>
 * 代码测试：使用第一种得到的是[0，0]，第二种得到的是[0, 117]
 */

public class GetAbsluteLocView extends View {
    public GetAbsluteLocView(Context context) {
        this(context, null);
    }

    public GetAbsluteLocView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        float[] values = new float[9];
        int[] location1 = new int[2];

        Matrix matrix = canvas.getMatrix();
        matrix.getValues(values);

        location1[0] = (int) values[2];
        location1[1] = (int) values[5];
        Log.e("----------", Arrays.toString(location1));

        int[] location2 = new int[2];
        this.getLocationOnScreen(location2);
        Log.e("----------", Arrays.toString(location2));
    }
}
