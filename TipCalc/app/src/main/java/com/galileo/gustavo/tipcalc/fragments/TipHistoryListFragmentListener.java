package com.galileo.gustavo.tipcalc.fragments;

import com.galileo.gustavo.tipcalc.models.TipRecord;

/**
 * Created by gustavo on 31/05/16.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
