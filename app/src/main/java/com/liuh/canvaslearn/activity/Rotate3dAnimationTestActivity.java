package com.liuh.canvaslearn.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.liuh.canvaslearn.R;
import com.liuh.canvaslearn.animation.Rotate3dAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Rotate3dAnimationTestActivity extends AppCompatActivity {

    @BindView(R.id.iv_girl)
    ImageView ivGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3d_animation_test);
        ButterKnife.bind(this);

//        Window

        ViewPager.DecorView phoneWindow;
    }

    @OnClick({R.id.iv_girl})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_girl:
                Rotate3dAnimation animation = new Rotate3dAnimation(this, 0, 180,
                        ivGirl.getWidth() / 2.0f, ivGirl.getHeight() / 2.0f, 0, true);
                animation.setDuration(3000);//设置动画时长
                animation.setFillAfter(true);//保持旋转后效果
                animation.setInterpolator(new LinearInterpolator());//设置插值器
                ivGirl.startAnimation(animation);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
