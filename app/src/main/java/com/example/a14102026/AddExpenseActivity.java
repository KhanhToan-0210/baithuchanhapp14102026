package com.example.a14102026;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAmount;
    private Spinner spCategory;
    private DatePicker dpDate;
    private EditText etNote;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etName = findViewById(R.id.etName);
        etAmount = findViewById(R.id.etAmount);
        spCategory = findViewById(R.id.spCategory);
        dpDate = findViewById(R.id.dpDate);
        etNote = findViewById(R.id.etNote);
        btnSave = findViewById(R.id.btnSave);

        // Setup spinner
        String[] categories = {"Ăn uống", "Đi lại", "Học tập", "Giải trí", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String amountStr = etAmount.getText().toString().trim();
                String category = spCategory.getSelectedItem().toString();
                String note = etNote.getText().toString().trim();

                int day = dpDate.getDayOfMonth();
                int month = dpDate.getMonth() + 1; // month is 0-based
                int year = dpDate.getYear();
                String date = String.format("%02d/%02d/%04d", day, month, year);

                if (name.isEmpty()) {
                    etName.setError("Không được để trống");
                    return;
                }

                if (amountStr.isEmpty()) {
                    etAmount.setError("Không được để trống");
                    return;
                }

                double amount = 0;
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    etAmount.setError("Số tiền không hợp lệ");
                    return;
                }

                if (amount <= 0) {
                    etAmount.setError("Số tiền phải lớn hơn 0");
                    return;
                }

                Expense expense = new Expense(name, amount, category, date, note);
                ExpenseData.getInstance().addExpense(expense);

                Toast.makeText(AddExpenseActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                finish(); // Return to previous screen
            }
        });
    }
}
