package com.liuh.canvaslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_drawColorView, R.id.btn_drawPointView, R.id.btn_drawLineView, R.id.btn_drawRectView,
            R.id.btn_drawRoundCorneRectView, R.id.btn_drawOvalView, R.id.btn_drawCircleView, R.id.btn_drawArcView,
            R.id.btn_paintUseLearnView, R.id.btn_drawPieView})
    void onBtnClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_drawColorView:
                intent.setClass(MainActivity.this, DrawColorViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawPointView:
                intent.setClass(MainActivity.this, DrawPointViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawLineView:
                intent.setClass(MainActivity.this, DrawLineViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawRectView:
                intent.setClass(MainActivity.this, DrawRectViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawRoundCorneRectView:
                intent.setClass(MainActivity.this, DrawRoundCornerRectViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawOvalView:
                intent.setClass(MainActivity.this, DrawOvalViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawCircleView:
                intent.setClass(MainActivity.this, DrawCircleViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawArcView:
                intent.setClass(MainActivity.this, DrawArcViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_paintUseLearnView:
                intent.setClass(MainActivity.this, PaintUseLearnViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawPieView:
                intent.setClass(MainActivity.this, DrawPieViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;


        }
    }

}
