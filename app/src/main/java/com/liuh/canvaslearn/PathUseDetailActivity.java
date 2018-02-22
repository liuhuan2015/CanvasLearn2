package com.liuh.canvaslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    @OnClick({R.id.btn_pathUseMethodDetailFirstGroup})
    void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_pathUseMethodDetailFirstGroup:
                intent.setClass(PathUseDetailActivity.this, PathUseDetailFirstGroupActivity.class);
                startActivity(intent);
                break;

        }


    }


}
