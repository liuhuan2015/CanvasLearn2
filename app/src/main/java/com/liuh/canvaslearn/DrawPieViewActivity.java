package com.liuh.canvaslearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liuh.canvaslearn.model.PieData;
import com.liuh.canvaslearn.widget.PieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawPieViewActivity extends AppCompatActivity {

    @BindView(R.id.pieView)
    PieView mPieView;

    ArrayList<PieData> mPieData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_pie_view);
        ButterKnife.bind(this);

        initPieView();
    }

    private void initPieView() {
        PieData pieData = new PieData("华为", 5333);
        mPieData.add(pieData);

        PieData pieData1 = new PieData("小米", 2111);
        mPieData.add(pieData1);

        PieData pieData2 = new PieData("一加", 1900);
        mPieData.add(pieData2);

        PieData pieData3 = new PieData("oppo", 1700);
        mPieData.add(pieData3);

        PieData pieData4 = new PieData("vivo", 1500);
        mPieData.add(pieData4);

        PieData pieData5 = new PieData("锤子", 990);
        mPieData.add(pieData5);

        mPieView.setData(mPieData);
    }
}
