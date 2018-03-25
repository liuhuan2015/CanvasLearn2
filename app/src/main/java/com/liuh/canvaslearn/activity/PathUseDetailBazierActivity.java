package com.liuh.canvaslearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Path中的Bazier曲线
 */
public class PathUseDetailBazierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail_bazier);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_PathUseDetailBazierSecond, R.id.btn_PathUseDetailBazierThird})
    void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_PathUseDetailBazierSecond:
                intent.setClass(this, PathUseDetailBazierSecondActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_PathUseDetailBazierThird:
                intent.setClass(this, PathUseDetailBazierThirdActivity.class);
                startActivity(intent);
                break;
        }
    }

}
