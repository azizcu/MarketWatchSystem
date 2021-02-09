package com.example.marketwatchsystem.Model;

import android.content.Context;

public class Builder {
    private String productName,productCode,dept;
    Context context;

    public Builder(String productName, String productCode, Context context) {
        this.productName = productName;
        this.productCode = productCode;
        this.context = context;
    }

    public Builder setDept(String dept) {
        this.dept = dept;
        return this;
    }

    public SendInfo Build() {
        SendInfo si = new SendInfo(productName,productCode,dept,context);
        return si;
    }
}
