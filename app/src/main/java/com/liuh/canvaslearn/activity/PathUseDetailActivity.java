package com.liuh.canvaslearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Path的使用方法详解
 */
public class PathUseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_pathUseMethodDetailFirstGroup, R.id.btn_pathUseMethodDetailAddXxx,
            R.id.btn_pathUseMethodDetailAddPath, R.id.btn_pathUseMethodDetailAddArc,
            R.id.btn_pathUseMethodDetailArcTo, R.id.btn_pathUseMethodDetailOffset,
            R.id.btn_pathUseMethodDetailRadarView})
    void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_pathUseMethodDetailFirstGroup:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailFirstGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailAddXxx:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailAddXxxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailAddPath:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailAddPathActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailAddArc:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailAddArcActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailArcTo:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailArcToActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailOffset:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailOffsetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pathUseMethodDetailRadarView:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailRadarViewActivity.class);
                startActivity(intent);
                break;
        }


    }


}
