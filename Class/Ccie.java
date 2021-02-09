package com.example.marketwatchsystem.Class;

import android.content.Context;
import android.widget.Toast;

import com.example.marketwatchsystem.Interface.ShowData;

public class Ccie implements ShowData {
    Context context;

    public Ccie(Context context) {
        this.context = context;
    }

    @Override
    public void showToast() {
        Toast.makeText(context, "Enter required info of CCIE Dept",
                Toast.LENGTH_SHORT).show();
    }
}
