package com.example.marketwatchsystem.Class;

import android.content.Context;
import android.widget.Toast;

import com.example.marketwatchsystem.Interface.ShowData;

public class Nbr implements ShowData {
    Context context;

    public Nbr(Context context) {
        this.context = context;
    }

    @Override
    public void showToast() {
        Toast.makeText(context, "Enter required info of NBR Dept",
                Toast.LENGTH_SHORT).show();
    }
}
