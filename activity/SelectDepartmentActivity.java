package com.example.marketwatchsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marketwatchsystem.Class.Agri;
import com.example.marketwatchsystem.Class.Ccie;
import com.example.marketwatchsystem.Class.Nbr;
import com.example.marketwatchsystem.R;
import com.example.marketwatchsystem.Interface.ShowData;
import com.example.marketwatchsystem.Class.Tcb;

public class SelectDepartmentActivity extends AppCompatActivity implements View.OnClickListener {
    private String type;
    private Button btn_agri,btn_tcb,btn_nbr,btn_cci;
    ShowData showData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_department);

        btn_agri = findViewById(R.id.button_agri);
        btn_tcb = findViewById(R.id.button_tcb);
        btn_nbr = findViewById(R.id.button_nbr);
        btn_cci = findViewById(R.id.button_cci);


        Intent intent = getIntent();
        if(intent.getStringExtra("message").equals("add")){
            type="add";
        }
        else  if(intent.getStringExtra("message").equals("update")){
            type="update";
        }

        btn_agri.setOnClickListener(this);
        btn_tcb.setOnClickListener(this);
        btn_nbr.setOnClickListener(this);
        btn_cci.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_agri :
                showData = new Agri(SelectDepartmentActivity.this);
                Intent intent_agri = new Intent(SelectDepartmentActivity.this, AgriculturalDeptActivity.class);
                intent_agri.putExtra("type",type);
                startActivity(intent_agri);
                showData.showToast();
                break;
            case R.id.button_tcb :
                showData = new Tcb(SelectDepartmentActivity.this);
                Intent intent_tcb = new Intent(SelectDepartmentActivity.this, TCBDeptActivity.class);
                intent_tcb.putExtra("type",type);
                startActivity(intent_tcb);
                showData.showToast();
                break;
            case R.id.button_nbr :
                showData = new Nbr(SelectDepartmentActivity.this);
                Intent intent_nbr = new Intent(SelectDepartmentActivity.this, NBRDeptActivity.class);
                intent_nbr.putExtra("type",type);
                startActivity(intent_nbr);
                showData.showToast();
                break;
            case R.id.button_cci :
                showData = new Ccie(SelectDepartmentActivity.this);
                Intent intent_cci = new Intent(SelectDepartmentActivity.this, CCIEDeptActivity.class);
                intent_cci.putExtra("type",type);
                startActivity(intent_cci);
                showData.showToast();
                break;
        }
    }
}