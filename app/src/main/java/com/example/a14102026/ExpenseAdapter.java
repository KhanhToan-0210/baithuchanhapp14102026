package com.example.a14102026;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    private Context context;
    private List<Expense> expenses;

    public ExpenseAdapter(Context context, List<Expense> expenses) {
        super(context, 0, expenses);
        this.context = context;
        this.expenses = expenses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        }

        Expense expense = expenses.get(position);

        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvAmount = convertView.findViewById(R.id.tvItemAmount);
        TextView tvCategory = convertView.findViewById(R.id.tvItemCategory);
        TextView tvDate = convertView.findViewById(R.id.tvItemDate);

        tvName.setText(expense.getName());
        
        DecimalFormat formatter = new DecimalFormat("#,###");
        tvAmount.setText(formatter.format(expense.getAmount()) + " đ");
        
        tvCategory.setText(expense.getCategory());
        tvDate.setText(expense.getDate());

        return convertView;
    }
}
