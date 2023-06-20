package fr.melaine.gerard.tradeflow.model;

public class PaymentMethod {
    private final int id;
    private final String name;

    public PaymentMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }
}
