package com.example.khaata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khaata.TransactionDetailsActivity;
import com.example.khaata.databinding.DataRecyclerViewBinding;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DetailsViewHolder>{
    private ArrayList<ExpenseList> expenseLists;
    private Context context;

    public RecyclerAdapter(ArrayList<ExpenseList> expenseLists, Context context) {
        this.expenseLists = expenseLists;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataRecyclerViewBinding binding = DataRecyclerViewBinding.inflate(LayoutInflater.from(context));
        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.title.setText(expenseLists.get(position).getTitle());
        holder.desc.setText(expenseLists.get(position).getDescription());
        holder.date.setText(expenseLists.get(position).getTDate());
        holder.totalTranTxt.setText("₹ "+ expenseLists.get(position).getTotal());
        holder.itemView.setOnClickListener(view -> {
            Intent transactionDetails = new Intent(context, TransactionDetailsActivity.class);
            transactionDetails.putExtra("TRANSACTION", (Parcelable) expenseLists.get(position));
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView totalTranTxt,title,desc,date;
        public DetailsViewHolder(@NonNull DataRecyclerViewBinding binding) {
            super(binding.getRoot());
            totalTranTxt = binding.totalTranTxt;
            title = binding.titleTxt;
            desc = binding.descTxt;
            date = binding.dateTxt;

        }
    }
}
