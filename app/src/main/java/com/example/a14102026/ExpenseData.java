package com.example.a14102026;

import java.util.ArrayList;
import java.util.List;

public class ExpenseData {
    private static ExpenseData instance;
    private List<Expense> expenseList;

    private ExpenseData() {
        expenseList = new ArrayList<>();
        // Add sample data
        expenseList.add(new Expense("Ăn sáng", 30000, "Ăn uống", "14/10/2026", ""));
        expenseList.add(new Expense("Đổ xăng", 50000, "Đi lại", "14/10/2026", ""));
        expenseList.add(new Expense("Mua sách", 120000, "Học tập", "14/10/2026", ""));
    }

    public static ExpenseData getInstance() {
        if (instance == null) {
            instance = new ExpenseData();
        }
        return instance;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public void removeExpense(String id) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getId().equals(id)) {
                expenseList.remove(i);
                break;
            }
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (Expense expense : expenseList) {
            total += expense.getAmount();
        }
        return total;
    }

    public Expense getExpenseById(String id) {
        for (Expense expense : expenseList) {
            if (expense.getId().equals(id)) {
                return expense;
            }
        }
        return null;
    }
}
