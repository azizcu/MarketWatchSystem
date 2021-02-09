package com.example.marketwatchsystem.Class;

import android.content.Context;
import android.widget.Toast;

import com.example.marketwatchsystem.Interface.ShowData;

public class Tcb implements ShowData {
    Context context;

    public Tcb(Context context) {
        this.context = context;
    }

    @Override
    public void showToast() {
        Toast.makeText(context, "Enter required info of TCB Dept",
                Toast.LENGTH_SHORT).show();
    }
}
