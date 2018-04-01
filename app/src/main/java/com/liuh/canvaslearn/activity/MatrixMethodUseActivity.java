package com.liuh.canvaslearn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixMethodUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_method_use);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_MatrixMapPointsUse, R.id.btn_MatrixMapRadiusUse, R.id.btn_MatrixMapRectUse,
            R.id.btn_MatrixMapVectorsUse})
    void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_MatrixMapPointsUse:
                intent.setClass(this, MatrixMapPointsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_MatrixMapRadiusUse:
                intent.setClass(this, MatrixMapRadiusActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_MatrixMapRectUse:
                intent.setClass(this, MatrixMapRectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_MatrixMapVectorsUse:
                intent.setClass(this, MatrixMapVectorsActivity.class);
                startActivity(intent);
                break;
        }
    }

}
