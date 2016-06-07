package com.galileo.gustavo.tipcalc.activities;

import android.app.Application;

/**
 * Created by gustavo on 31/05/16.
 */
public class TipCalcApp extends Application {
    private static final String URL_ABOUT="http://google.com";

    public  String getUrlAbout() {
        return URL_ABOUT;
    }
}
