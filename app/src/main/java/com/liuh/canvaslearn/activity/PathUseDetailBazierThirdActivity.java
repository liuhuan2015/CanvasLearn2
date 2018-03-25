package com.liuh.canvaslearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.liuh.canvaslearn.R;
import com.liuh.canvaslearn.widget.PathUseDetailBazierThirdOrderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Path中的Bazier曲线(三阶)
 */
public class PathUseDetailBazierThirdActivity extends AppCompatActivity {

    @BindView(R.id.bazier_thirdorder)
    PathUseDetailBazierThirdOrderView bazierThirdorder;

    @BindView(R.id.rg_control)
    RadioGroup rgControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_use_detail_bazier_third);
        ButterKnife.bind(this);

        rgControl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.e("----------", "------i:" + i);

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_control1:
                        bazierThirdorder.setMode(true);
                        break;
                    case R.id.rb_control2:
                        bazierThirdorder.setMode(false);
                        break;
                }
            }
        });
    }


}
