package com.liuh.canvaslearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.R;
import com.liuh.canvaslearn.widget.MatrixPolyToPolyTestView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Matrix类的setPolyToPoly(...)方法中的pointCount数值设置测试
 */
public class MatrixSetPolyToPolyPointTestActivity extends AppCompatActivity {

    @BindView(R.id.polytopoly_testView)
    MatrixPolyToPolyTestView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_set_poly_to_poly_point_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rb_zero, R.id.rb_one, R.id.rb_two, R.id.rb_three, R.id.rb_four})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_zero:
                testView.setTestPoint(0);
                break;
            case R.id.rb_one:
                testView.setTestPoint(1);
                break;
            case R.id.rb_two:
                testView.setTestPoint(2);
                break;
            case R.id.rb_three:
                testView.setTestPoint(3);
                break;
            case R.id.rb_four:
                testView.setTestPoint(4);
                break;
        }

    }

}
