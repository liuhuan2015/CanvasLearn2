package com.liuh.canvaslearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Canvas绘制的基础使用
 */
public class CanvasBasicDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_basic_draw);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_drawColorView, R.id.btn_drawPointView, R.id.btn_drawLineView, R.id.btn_drawRectView,
            R.id.btn_drawRoundCorneRectView, R.id.btn_drawOvalView, R.id.btn_drawCircleView, R.id.btn_drawArcView})
    void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_drawColorView:
                intent.setClass(this, DrawColorViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawPointView:
                intent.setClass(this, DrawPointViewActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_drawLineView:
                intent.setClass(this, DrawLineViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawRectView:
                intent.setClass(this, DrawRectViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawRoundCorneRectView:
                intent.setClass(this, DrawRoundCornerRectViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawOvalView:
                intent.setClass(this, DrawOvalViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawCircleView:
                intent.setClass(this, DrawCircleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawArcView:
                intent.setClass(this, DrawArcViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
