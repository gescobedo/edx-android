package com.galileo.gustavo.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.galileo.gustavo.tipcalc.R;
import com.galileo.gustavo.tipcalc.fragments.TipHistoryListFragment;
import com.galileo.gustavo.tipcalc.fragments.TipHistoryListFragmentListener;
import com.galileo.gustavo.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.separator)
    View separator;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) about();
        return super.onOptionsItemSelected(item);
    }

    public void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String url = app.getUrlAbout();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit() {
        Log.e(getLocalClassName(), "click en submit");
        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            //double tip = total * (tipPercentage / 100d);

            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }


    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease() {
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease() {
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    public void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage > 0) {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    @OnClick(R.id.btnClear)
    public void handleClearClick() {
        fragmentListener.clearList();

    }

    public int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInptuTipPercentage = inputPercentage.getText().toString().trim();
        if (!strInptuTipPercentage.isEmpty()) {
            tipPercentage = Integer.parseInt(strInptuTipPercentage);
        } else {
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTAGE));
        }
        return tipPercentage;

    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken()
                    , InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));


        }

    }

}
