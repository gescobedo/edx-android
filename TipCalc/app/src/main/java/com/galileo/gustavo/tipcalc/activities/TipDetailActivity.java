package com.galileo.gustavo.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.galileo.gustavo.tipcalc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @Bind(R.id.txtViewTotal)
    TextView txtViewTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;
    public final  static String TIP_KEY="tip";
    public final  static String DATE_KEY="date";
    public final  static String BILL_TOTAL_KEY="total";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);
        Intent intent= getIntent();
        String strTotal=String.format(getString(R.string.tipDetail_message_bill)
                ,intent.getDoubleExtra(BILL_TOTAL_KEY,0d));
        String strTip=String.format(getString(R.string.global_message_tip)
                ,intent.getDoubleExtra(TIP_KEY,0d));
        txtViewTotal.setText(strTotal);
        txtTip.setText(strTip);
        txtTimeStamp.setText(intent.getStringExtra(DATE_KEY));
    }

}
