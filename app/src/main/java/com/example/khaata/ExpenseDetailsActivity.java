package com.example.khaata;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaata.databinding.ActivityExpenseDetailsBinding;
import com.example.khaata.entity.ExpenseDetails;
import com.example.khaata.entity.ExpenseList;

import java.util.ArrayList;

public class ExpenseDetailsActivity extends AppCompatActivity {

    private ActivityExpenseDetailsBinding binding;
    private ExpenseList expenseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpenseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent data = getIntent();
        if(data!=null)
        {
             expenseList =  data.getParcelableExtra("exList");
            binding.dtText.setText(expenseList.getTDate());
            binding.eTitle.setText(expenseList.getTitle());
            binding.descTxt.setText(expenseList.getDescription());
            int sr_no = 0;
            ArrayList<ExpenseDetails> details = data.getParcelableArrayListExtra("exDetails");
            for(ExpenseDetails expenseDetails : details)
            {
                createDetailsTable(expenseDetails.getItemName(),expenseDetails.getItemDesc(), (float) expenseDetails.getPrice(),++sr_no+".");
            }
        }


    }
    private void createDetailsTable(String itemName,String itemDes,float price,String sr_no)
    {
        TableRow row = new TableRow(this);

        final LinearLayout.LayoutParams[] p = {new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)};
        row.setLayoutParams(p[0]);

        TextView iNm = new TextView(this);
        TextView iQ = new TextView(this);
        TextView iP = new TextView(this);
        TextView sr = new TextView(this);

        sr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        iNm.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        iP.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        iQ.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));

        iNm.setBackgroundColor(getResources().getColor(R.color.white));
        sr.setBackgroundColor(getResources().getColor(R.color.white));

        iNm.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        sr.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));

        iNm.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iNm.setTextColor(getResources().getColor(R.color.black));
        sr.setTextColor(getResources().getColor(R.color.black));


        iP.setBackgroundColor(getResources().getColor(R.color.white));
        iP.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        iP.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iP.setTextColor(getResources().getColor(R.color.black));


        iQ.setBackgroundColor(getResources().getColor(R.color.white));
        iQ.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        iQ.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iQ.setTextColor(getResources().getColor(R.color.black));

        iNm.setText(itemName);
        iP.setText(String.valueOf(price));
        iQ.setText(itemDes);
        sr.setText(sr_no);

        row.addView(sr);
        row.addView(iNm);
        row.addView(iQ);
        row.addView(iP);
        binding.detailsTable.addView(row);
    }
    private float convertDpToPx(float dp) {
        return dp * this.getResources().getDisplayMetrics().density;
    }

}