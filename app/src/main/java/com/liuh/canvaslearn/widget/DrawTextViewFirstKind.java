package com.liuh.canvaslearn.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/1/29 14:34
 * Description:绘制文字的第一类操作:可以指定文本基线的位置(基线x默认在字符串左侧,基线y默认在字符串下方)
 */

public class DrawTextViewFirstKind extends View {

    private Paint textPaint = new Paint();

    public DrawTextViewFirstKind(Context context) {
        this(context, null);
    }

    public DrawTextViewFirstKind(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "ABCDEFG";
        //基线x默认在字符串左侧,基线y默认在字符串下方
        canvas.drawText(text, 200, 500, textPaint);

        //字符串截取,含头不含尾
        canvas.drawText(text, 1, 4, 200, 700, textPaint);

        //字符数组,含首位,尾位
        char[] chars = text.toCharArray();

        canvas.drawText(chars, 1, 5, 200, 900, textPaint);

    }
}
