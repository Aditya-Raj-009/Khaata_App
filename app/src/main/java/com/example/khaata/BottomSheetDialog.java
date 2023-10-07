package com.example.khaata;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.khaata.databinding.BottoSheetDialogBinding;
import com.example.khaata.entity.ExpenseDetails;
import com.example.khaata.entity.ExpenseList;
import com.example.khaata.viewModel.ViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private static double total;
    private static BottoSheetDialogBinding binding;
    private static ExpenseList expenseList;
    private static ArrayList<ExpenseDetails> expenseDetailsArrayList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.botto_sheet_dialog, container, false);

        expenseDetailsArrayList = new ArrayList<>();
        expenseList = new ExpenseList();
        expenseList.setExpList(expenseDetailsArrayList);
        Toast.makeText(getContext(), total+" "+expenseList.getTotal()+" "+expenseDetailsArrayList.size(), Toast.LENGTH_SHORT).show();
        this.setCancelable(false);
        binding.totalBtn.setText("Total: "+String.format("%.2f",total));

        binding.closeBottomSheet.setOnClickListener(view -> {
            total = 0;
           expenseList.setExpList(null);
            binding.expTable.removeAllViews();
            dismiss();
        });

        binding.addMore.setOnClickListener(view -> {
            addRow();
        });

        return binding.getRoot();
    }

    public float convertDpToPx(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }

    private void addRow() {
        ExpenseDetails expenseDetails = new ExpenseDetails();
        final String[] itemName = {null};
        final double[] quantity = {0};
        final double[] price = { 0 };
        final boolean[] flag = {true};

        TableRow row = new TableRow(getContext());

        final LinearLayout.LayoutParams[] p = {new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)};
        row.setLayoutParams(p[0]);
        row.setBackgroundColor(getResources().getColor(R.color.black));

        EditText iNm = new EditText(getContext());
        EditText iQ = new EditText(getContext());
        EditText iP = new EditText(getContext());

        iP.addTextChangedListener(new TextWatcher() {
            ExpenseDetails details = new ExpenseDetails();
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().isEmpty())
                {
                    expenseList.getExpList().remove(expenseDetails);
                    if(itemName[0]!=null &&itemName[0].length()!=0 && total>0 && price[0]*quantity[0]<=total) {
                        total -= (price[0] * quantity[0]);
                    }
                   price[0]=0;
                    calculate(itemName[0],quantity[0],0,expenseDetails);

                    return;

                }
                if(!editable.toString().trim().isEmpty()){
                    if(itemName[0]!=null && itemName[0].length()!=0 && total>0 && price[0]*quantity[0]<=total) {
                        total -= (price[0] * quantity[0]);
                    }
                    if(editable.toString().trim().startsWith("."))
                    {
                        price[0] = Double.parseDouble(0+editable.toString());
                    }
                    else{
                        price[0] = Double.parseDouble(editable.toString());
                    }


                }
               calculate(itemName[0], quantity[0], price[0],expenseDetails);

            }
        });
        iNm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().trim().isEmpty()) {

                    itemName[0] = editable.toString().trim();

                    if(flag[0])
                    {
                        calculate(itemName[0], quantity[0], price[0],expenseDetails);
                        flag[0] = false;

                    }




                }
                else{
                    expenseList.getExpList().remove(expenseDetails);
                    flag[0]=true;

                    if(total>0 && price[0]*quantity[0]<=total && price[0]!=0 && quantity[0]!=0) {
                        total-=(price[0]*quantity[0]);
                        calculate(itemName[0], 0, 0,expenseDetails);
                    }
                    itemName[0] = "";


                    }


                }
        });
        iQ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!editable.toString().trim().isEmpty())
                {
                    if(itemName[0]!=null &&itemName[0].length()!=0 && total>0 && price[0]*quantity[0]<=total) {
                        total -= (price[0] * quantity[0]);
                    }
                    quantity[0] = Double.parseDouble(editable.toString());


                }
                else{
                    expenseList.getExpList().remove(expenseDetails);

                    if(itemName[0]!=null && itemName[0].length()!=0 && total>0 && price[0]*quantity[0]<=total) {
                       total-=(price[0]*quantity[0]);

                    }

                    quantity[0] = 0;
                }

                calculate(itemName[0], quantity[0], price[0],expenseDetails);

            }
        });
        ImageButton delete = new ImageButton(getContext());


        // Set layout_weight to distribute space evenly
        iNm.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        iP.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        iQ.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        delete.setLayoutParams(new TableRow.LayoutParams((int) convertDpToPx(25), TableRow.LayoutParams.MATCH_PARENT));
        delete.setImageDrawable(getResources().getDrawable(R.drawable.baseline_delete_24));
        delete.setBackgroundColor(getResources().getColor(R.color.white));
        delete.setOnClickListener(view -> {
            binding.expTable.removeView((View) view.getParent());

            if(total>0 && price[0]*quantity[0]<=total)
            {

                total-=(price[0]*quantity[0]);
                calculate(itemName[0],0,0,expenseDetails);
                expenseList.getExpList().remove(expenseDetails);
                Toast.makeText(getContext(), expenseDetails.getPrice()+" "+expenseDetails.getQuantity()
                        +"\n"+expenseList.getTotal()+" "+expenseList.getExpList().size(), Toast.LENGTH_SHORT).show();

            }
        });

        // Set background color and other properties for EditText fields
        iNm.setBackgroundColor(getResources().getColor(R.color.white));
        iNm.setHint("Item name");
        iNm.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        iNm.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iNm.setTextColor(getResources().getColor(R.color.black));
        iNm.setHintTextColor(getResources().getColor(R.color.gray));

        iP.setBackgroundColor(getResources().getColor(R.color.white));
        iP.setHint("Price");
        iP.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        iP.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        iP.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iP.setTextColor(getResources().getColor(R.color.black));
        iP.setHintTextColor(getResources().getColor(R.color.gray));


        iQ.setBackgroundColor(getResources().getColor(R.color.white));
        iQ.setHint("Quantity");
        iQ.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        iQ.setPadding((int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2), (int) convertDpToPx(2));
        iQ.setMaxWidth((int) convertDpToPx(100)); // Adjust max width as needed
        iQ.setTextColor(getResources().getColor(R.color.black));
        iQ.setHintTextColor(getResources().getColor(R.color.gray));


        row.addView(iNm);
        row.addView(iQ);
        row.addView(iP);
        row.addView(delete);


        binding.expTable.addView(row);

    }

    private void calculate(String itemName, double quantity, double price,ExpenseDetails details) {

        if(itemName==null || itemName.isEmpty())
        {
            return;
        }
        if((int)quantity<Integer.MAX_VALUE && (int)price<Integer.MAX_VALUE&&(int)total<=Integer.MAX_VALUE) {
            total += (price * quantity);
            binding.totalBtn.setText("Total: "+String.format("%.2f",total));
            details.setPrice(price);
            details.setQuantity(quantity);
            expenseList.setTotal(total);
            if(price>0 && quantity>0 && !expenseDetailsArrayList.contains(details)) {
                expenseDetailsArrayList.add(details);
            }
            Toast.makeText(getContext(), details.getPrice()+" "+details.getQuantity()+"\n" +
                    expenseList.getTotal()+" "+expenseList.getExpList().size(), Toast.LENGTH_SHORT).show();

        }



    }




}
