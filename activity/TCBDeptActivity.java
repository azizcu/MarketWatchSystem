package com.example.marketwatchsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marketwatchsystem.Class.DBHelper;
import com.example.marketwatchsystem.Interface.InsertData;
import com.example.marketwatchsystem.R;
import com.google.android.material.textfield.TextInputEditText;

public class TCBDeptActivity extends AppCompatActivity implements InsertData {
    private TextInputEditText edit_text_local_price,edit_text_international_price;
    private Button btn_add,btn_update;
    private Spinner spinner_select_prd,spinner_select_prdCd;
    private DBHelper mydb ;
    public static TCBDeptActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_c_b_dept);
        mydb = new DBHelper(this);
        INSTANCE = this;
        init();

        ArrayAdapter<String> productName =new ArrayAdapter<String>(TCBDeptActivity.this,android.R.layout.simple_spinner_item,
                mydb.getAllProductNames());
        productName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_prd.setAdapter(productName);

        ArrayAdapter<String> productCode =new ArrayAdapter<String>(TCBDeptActivity.this,android.R.layout.simple_spinner_item,
                mydb.getAllProductCodes());
        productCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_prdCd.setAdapter(productCode);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataIntoDb();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataIntoDb();
            }
        });

        Intent intent = getIntent();
        if(intent.getStringExtra("type").equals("add")){
            btn_update.setVisibility(View.GONE);
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();
        }
        else  if(intent.getStringExtra("type").equals("update")){
            btn_add.setVisibility(View.GONE);
        }
    }

    public static TCBDeptActivity getActivityInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addDataIntoDb() {
        if(mydb.insertTcb(spinner_select_prd.getSelectedItem().toString(),spinner_select_prdCd.getSelectedItem().toString(),
                edit_text_local_price.getText().toString(),edit_text_international_price.getText().toString())){
            Toast.makeText(getApplicationContext(), "done",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(), "not done",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateDataIntoDb() {
        if(mydb.updateTcb(spinner_select_prd.getSelectedItem().toString(),spinner_select_prdCd.getSelectedItem().toString(),
                edit_text_local_price.getText().toString(),edit_text_international_price.getText().toString())){
            Toast.makeText(getApplicationContext(), "done",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(), "not done",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void init(){
        btn_add = findViewById(R.id.btn_add_tcb);
        btn_update = findViewById(R.id.btn_update_tcb);
        spinner_select_prd = findViewById(R.id.spinner_select_prd_tcb);
        spinner_select_prdCd = findViewById(R.id.spinner_select_prdCd_tcb);

        edit_text_local_price = findViewById(R.id.edit_text_local_pri);
        edit_text_international_price = findViewById(R.id.edit_text_inter_pri);

    }
}