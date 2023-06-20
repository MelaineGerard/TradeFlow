package fr.melaine.gerard.tradeflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.melaine.gerard.tradeflow.TradeFlow;

public class SellReport {
    private final int id;
    private final Map<PaymentMethod, List<Transaction>> transactions;
    private final User user;

    public SellReport(int id, User user) {
        this.id = id;
        this.user = user;
        this.transactions = new HashMap<>();

        for (PaymentMethod paymentMethod : TradeFlow.getPaymentMethods()) {
            this.transactions.put(paymentMethod, new ArrayList<>());
        }
    }

    public int getId() {
        return id;
    }

    public Map<PaymentMethod, List<Transaction>> getTransactions() {
        return transactions;
    }

    public User getUser() {
        return user;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.get(transaction.getPaymentMethod()).add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        this.transactions.get(transaction.getPaymentMethod()).remove(transaction);
    }

    public int getTotal() {
        int total = 0;

        for (Map.Entry<PaymentMethod, List<Transaction>> entry : transactions.entrySet()) {
            for (Transaction transaction : entry.getValue()) {
                total += transaction.getTotal();
            }
        }

        return total;
    }
}
