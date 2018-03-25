package com.liuh.canvaslearn.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huan on 2018/3/25.
 * Path使用中的三阶贝塞尔曲线
 */

public class PathUseDetailBazierThirdOrderView extends View {

    public PathUseDetailBazierThirdOrderView(Context context) {
        this(context,null);
    }

    public PathUseDetailBazierThirdOrderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathUseDetailBazierThirdOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
