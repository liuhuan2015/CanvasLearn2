package com.liuh.canvaslearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuh.canvaslearn.R;

/**
 * public void arcTo (RectF oval, float startAngle, float sweepAngle)
 * <p>
 * 添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点.
 * <p>
 * public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
 * forceMoveTo	含义	等价方法
 * true	将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点	public void addArc (RectF oval, float startAngle, float sweepAngle)
 * false	不移动，而是连接最后一个点与圆弧起点	public void arcTo (RectF oval, float startAngle, float sweepAngle)
 */
public class PathUseDetailArcToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail_arcto);
    }
}
