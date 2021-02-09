package com.example.marketwatchsystem.Class;

import android.content.Context;
import android.widget.Toast;

import com.example.marketwatchsystem.Interface.ShowData;

public class Agri implements ShowData {
    Context context;

    public Agri(Context context) {
        this.context = context;
    }

    @Override
    public void showToast() {
        Toast.makeText(context, "Enter required info of Agricultural Dept",
                Toast.LENGTH_SHORT).show();
    }
}
