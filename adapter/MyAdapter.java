package com.example.marketwatchsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketwatchsystem.Model.ProductInfo;
import com.example.marketwatchsystem.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ProductInfo> products = new ArrayList<>();
    private Context context;

    public MyAdapter(ArrayList<ProductInfo> products, Context context) {
        this.products = products;
        this.context = context;
    }


    @NonNull
    @Override

    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_products,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.name.setText(products.get(position).getProductName().toString());
        holder.message.setText(products.get(position).getMessage().toString());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,message;
        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.text_name);
            message = view.findViewById(R.id.text_msg);
        }
    }
}
