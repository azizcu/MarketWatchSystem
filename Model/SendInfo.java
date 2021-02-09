package com.example.marketwatchsystem.Model;

import android.content.Context;

import com.example.marketwatchsystem.Class.DBHelper;

public class SendInfo {
    private String productName,productCode,dept;
    Context context;
    private DBHelper mydb;

    public SendInfo(String productName, String productCode, String dept,Context context) {
        this.productName = productName;
        this.productCode = productCode;
        this.dept = dept;
        this.context=context;
        mydb = new DBHelper(context);
    }

    public boolean send(){
        if(mydb.insertDistributeInfo(productName,productCode,dept)){
            return true;
        } else{
            return false;
        }
    }
}
