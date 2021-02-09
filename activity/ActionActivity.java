package com.example.marketwatchsystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.marketwatchsystem.Class.DBHelper;
import com.example.marketwatchsystem.adapter.MyAdapter;
import com.example.marketwatchsystem.Model.ProductInfo;
import com.example.marketwatchsystem.R;

import java.util.ArrayList;

public class ActionActivity extends AppCompatActivity {
    private DBHelper mydb ;
    private RecyclerView recyclerView;
    ArrayList<ProductInfo> products;
    ArrayList<String> productName;
    ArrayList<String> productCode;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        mydb = new DBHelper(this);
        products = new ArrayList<ProductInfo>();

        loadMyRecyclerView();
        productName = mydb.getAllProductNames();
        productCode = mydb.getAllProductCodes();

        for(int i=0 ; i<productName.size() ; i++){
            ProductInfo productInfo = new ProductInfo(
                    productName.get(i),productCode.get(i),mydb.getMessage(String.valueOf(productCode.get(i)))
            );
            products.add(productInfo);
        }


    }

    private void loadMyRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(products,this);
        recyclerView.setAdapter(adapter);
    }
}