package fr.melaine.gerard.tradeflow.model;

import java.util.Map;

public class Transaction {
    private final int id;
    private final Client client;
    private final Map<Prestation, Integer> prestations;
    private final PaymentMethod paymentMethod;

    public Transaction(int id, Client client, Map<Prestation, Integer> prestations, PaymentMethod paymentMethod) {
        this.id = id;
        this.client = client;
        this.prestations = prestations;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Map<Prestation, Integer> getPrestations() {
        return prestations;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public int getTotal() {
        int total = 0;

        for (Map.Entry<Prestation, Integer> entry : prestations.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }
}
