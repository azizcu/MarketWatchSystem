package com.example.marketwatchsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marketwatchsystem.Model.Builder;
import com.example.marketwatchsystem.Class.DBHelper;
import com.example.marketwatchsystem.Model.Channel;
import com.example.marketwatchsystem.R;
import com.example.marketwatchsystem.Model.SendInfo;
import com.google.android.material.textfield.TextInputEditText;

public class DistributeInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edit_text_product_name, edit_text_product_code;
    private Button btn_send;
    private Spinner spinner_select_dept;
    private DBHelper mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribute_info);
        mydb = new DBHelper(this);
        init();
        btn_send.setOnClickListener(this);

        /*Channel ch = new Channel();
        ch.subscribe(AgriculturalDeptActivity.getActivityInstance());
        ch.subscribe(sb2);
        ch.subscribe(sb3);


        sb.setCh(ch);
        sb2.setCh(ch);
        sb3.setCh(ch);*/


    }


    public void init(){
        edit_text_product_name = findViewById(R.id.edit_text_prd_name);
        edit_text_product_code = findViewById(R.id.edit_text_prd_code);
        btn_send = findViewById(R.id.btn_send);
        spinner_select_dept = findViewById(R.id.spinner_select_dept);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_send){
            SendInfo si = new Builder(edit_text_product_name.getText().toString(),edit_text_product_code.getText().toString(),this).
                    setDept(spinner_select_dept.toString()).Build();

            if(si.send()){
                Toast.makeText(getApplicationContext(), "done",
                        Toast.LENGTH_SHORT).show();

            } else{
                Toast.makeText(getApplicationContext(), "not done",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}