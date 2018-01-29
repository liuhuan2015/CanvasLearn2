package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/29 15:03
 * Description:绘制文字的第二类操作:可以指定每个文字的位置
 * 作者不支持使用这种方法,因为:
 * 1,必须指定所有字符位置,否则会直接crash掉,反人类设计
 * 2,性能不佳,在大量使用的时候可能会导致卡顿
 * 3,不支持emoji等特殊字符,不支持字形组合与分解
 */

public class DrawTextViewSecondKind extends View {

    private Paint textPaint = new Paint();
    float[] floatPoint = new float[]{100, 100, 200, 200, 300, 300, 400, 400, 500, 500, 600, 600, 700, 700};

    public DrawTextViewSecondKind(Context context) {
        super(context);
    }

    public DrawTextViewSecondKind(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "ABCDEFG";

        canvas.drawPosText(text, floatPoint, textPaint);


    }
}
