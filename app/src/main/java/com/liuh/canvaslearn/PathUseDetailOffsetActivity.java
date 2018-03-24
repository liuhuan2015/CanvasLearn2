package com.liuh.canvaslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * public boolean isEmpty ()    判断path中是否包含内容。
 * <p>
 * Path path = new Path();
 * Log.e("1",path.isEmpty()+"");
 * <p>
 * path.lineTo(100,100);
 * Log.e("2",path.isEmpty()+"");
 * <p>
 * public boolean isRect (RectF rect)  判断path是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放进参数rect中。
 * <p>
 * path.lineTo(0,400);
 * path.lineTo(400,400);
 * path.lineTo(400,0);
 * path.lineTo(0,0);
 * <p>
 * RectF rect = new RectF();
 * boolean b = path.isRect(rect);
 * Log.e("Rect","isRect:"+b+"| left:"+rect.left+"| top:"+rect.top+"| right:"+rect.right+"| bottom:"+rect.bottom);
 * <p>
 * public void set (Path src)   将新的path赋值到现有path。
 * <p>
 * canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
 * canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
 * <p>
 * Path path = new Path();                     // path添加一个矩形
 * path.addRect(-200,-200,200,200, Path.Direction.CW);
 * <p>
 * Path src = new Path();                      // src添加一个圆
 * src.addCircle(0,0,100, Path.Direction.CW);
 * <p>
 * path.set(src);                              // 大致相当于 path = src;
 * <p>
 * canvas.drawPath(path,mPaint);
 * <p>
 * <p>
 * public void offset (float dx, float dy)
 * public void offset (float dx, float dy, Path dst)
 * <p>
 * 这个的作用也很简单，就是对path进行一段平移，它和Canvas中的translate作用很像，但Canvas作用于整个画布，而path的offset只作用于当前path。
 */
public class PathUseDetailOffsetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail_offset);
    }
}
