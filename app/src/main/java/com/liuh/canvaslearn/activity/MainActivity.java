package com.liuh.canvaslearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_canvasdraw_BasicUse, R.id.btn_paintUseLearnView, R.id.btn_drawPieView, R.id.btn_canvasTranslateView, R.id.btn_canvasScaleView,
            R.id.btn_canvasScaleViewOfForCircle, R.id.btn_canvasRotateView, R.id.btn_canvasRotateViewOfCircle,
            R.id.btn_canvasSkewView, R.id.btn_drawPictureView, R.id.btn_drawBitmapView, R.id.btn_drawBitmapViewOfCheckView,
            R.id.btn_drawTextViewFirstKind, R.id.btn_drawTextViewSecondKind, R.id.btn_pathUseMethodDetail,
            R.id.btn_pathUseMethodDetailBazier, R.id.btn_pathUseMethodDetailPathMeasure, R.id.btn_matrixMethodUse,
            R.id.btn_getLocationTest, R.id.btn_rotate3dAnimationTest, R.id.btn_RegionUse,
            R.id.btn_RegionUse_TouchLocTest, R.id.btn_RegionUse_RemoteControlMenu, R.id.btn_test_str,
            R.id.btn_face_frame})
    void onBtnClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_canvasdraw_BasicUse:
                intent.setClass(MainActivity.this, CanvasBasicDrawActivity.class);
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
            case R.id.btn_canvasTranslateView:
                intent.setClass(MainActivity.this, CanvasTranslateViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_canvasScaleView:
                intent.setClass(MainActivity.this, CanvasScaleViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_canvasScaleViewOfForCircle:
                intent.setClass(MainActivity.this, CanvasScaleViewOfForCircleActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_canvasRotateView:
                intent.setClass(MainActivity.this, CanvasRotateViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_canvasRotateViewOfCircle:
                intent.setClass(MainActivity.this, CanvasRotateViewOfCircleActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_canvasSkewView:
                intent.setClass(MainActivity.this, CanvasSkewViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawPictureView:
                intent.setClass(MainActivity.this, DrawPictureViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawBitmapView:
                intent.setClass(MainActivity.this, DrawBitmapViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawBitmapViewOfCheckView:
                intent.setClass(MainActivity.this, DrawBitmapViewOfCheckViewActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawTextViewFirstKind:
                intent.setClass(MainActivity.this, DrawTextViewFirstKindActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_drawTextViewSecondKind:
                intent.setClass(MainActivity.this, DrawTextViewSecondKindActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetail:
                intent.setClass(MainActivity.this, PathUseDetailActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailBazier:
                intent.setClass(MainActivity.this, PathUseDetailBazierActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailPathMeasure:
                intent.setClass(MainActivity.this, PathMeasureUseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_matrixMethodUse:
                intent.setClass(MainActivity.this, MatrixMethodUseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_getLocationTest:
                intent.setClass(this, GetLocationTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_rotate3dAnimationTest:
                intent.setClass(this, Rotate3dAnimationTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RegionUse:
                intent.setClass(this, RegionUseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RegionUse_TouchLocTest:
                intent.setClass(this, RegionUseTouchLocTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RegionUse_RemoteControlMenu:
                intent.setClass(this, RegionUseRemoteControlMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_str:
                intent.setClass(this, RegionUseRemoteControlMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_face_frame:
                intent.setClass(this, FaceRecognizeFrameActivity.class);
                startActivity(intent);
                break;
        }
    }
}
