package com.liuh.canvaslearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuh.canvaslearn.widget.CheckView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawBitmapViewOfCheckViewActivity extends AppCompatActivity {
    @BindView(R.id.checkView)
    CheckView mCheckView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap_view_of_check_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.checkView)
    void onViewClicked(View view) {
        if (mCheckView.isCheck()) {
            mCheckView.unCheck();
        } else {
            mCheckView.check();
        }
    }

}
