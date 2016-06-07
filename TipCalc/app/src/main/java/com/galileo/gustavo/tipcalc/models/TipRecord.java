package com.galileo.gustavo.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gustavo on 31/05/16.
 */
public class TipRecord {
    private double bill;
    private int percentage;
    private Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getFormattedDate() {
        SimpleDateFormat format= new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return format.format(timestamp);
    }
    public double getTip(){
        return bill*percentage/100d;
    }
}
