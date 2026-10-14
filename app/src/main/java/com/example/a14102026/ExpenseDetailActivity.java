package com.example.a14102026;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class ExpenseDetailActivity extends AppCompatActivity {

    private TextView tvDetailName;
    private TextView tvDetailAmount;
    private TextView tvDetailCategory;
    private TextView tvDetailDate;
    private TextView tvDetailNote;
    private Button btnBack;
    private Button btnDelete;
    private String expenseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailAmount = findViewById(R.id.tvDetailAmount);
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        tvDetailDate = findViewById(R.id.tvDetailDate);
        tvDetailNote = findViewById(R.id.tvDetailNote);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        expenseId = getIntent().getStringExtra("EXPENSE_ID");
        if (expenseId != null) {
            Expense expense = ExpenseData.getInstance().getExpenseById(expenseId);
            if (expense != null) {
                tvDetailName.setText(expense.getName());
                
                DecimalFormat formatter = new DecimalFormat("#,###");
                tvDetailAmount.setText(formatter.format(expense.getAmount()) + " đ");
                
                tvDetailCategory.setText(expense.getCategory());
                tvDetailDate.setText(expense.getDate());
                
                if (expense.getNote() != null && !expense.getNote().isEmpty()) {
                    tvDetailNote.setText(expense.getNote());
                } else {
                    tvDetailNote.setText("Không có ghi chú");
                }
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expenseId != null) {
                    ExpenseData.getInstance().removeExpense(expenseId);
                    Toast.makeText(ExpenseDetailActivity.this, "Đã xóa khoản chi", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
