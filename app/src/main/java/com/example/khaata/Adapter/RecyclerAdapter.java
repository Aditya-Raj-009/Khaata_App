package com.example.khaata.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khaata.databinding.DataRecyclerViewBinding;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DetailsViewHolder>{
    private ArrayList<ExpenseList> expenseListArrayList;
    private Context context;
    private ToTransDetails toTransDetails;

    public RecyclerAdapter(ArrayList<ExpenseList> expenseListArrayList, Context context,ToTransDetails toTransDetails) {
        this.expenseListArrayList = expenseListArrayList;
        this.context = context;
        this.toTransDetails = toTransDetails;
    }
    public void setExpenseLists(ArrayList<ExpenseList> expenseLists)
    {
        this.expenseListArrayList = expenseLists;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataRecyclerViewBinding binding = DataRecyclerViewBinding.inflate(LayoutInflater.from(context),
                parent,false);
        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.title.setText(expenseListArrayList.get(position).getTitle());
        holder.desc.setText(expenseListArrayList.get(position).getDescription());
        holder.date.setText(expenseListArrayList.get(position).getTDate());
        holder.totalTranTxt.setText("₹ "+ expenseListArrayList.get(position).getTotal());
        holder.itemView.setOnClickListener(view -> {
            toTransDetails.onClickDetails(expenseListArrayList.get(position));
        });


    }

    @Override
    public int getItemCount() {
        return expenseListArrayList.size();
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

  public interface ToTransDetails{
       void onClickDetails(ExpenseList expenseList);
    }
}
