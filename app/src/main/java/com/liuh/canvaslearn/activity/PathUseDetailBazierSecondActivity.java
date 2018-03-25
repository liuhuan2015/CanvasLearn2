package com.liuh.canvaslearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;

/**
 * Path中的Bazier曲线（二阶）
 */
public class PathUseDetailBazierSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail_bazier_second);
        ButterKnife.bind(this);
    }

}
