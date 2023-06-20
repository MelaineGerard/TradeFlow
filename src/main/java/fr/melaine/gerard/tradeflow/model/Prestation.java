package fr.melaine.gerard.tradeflow.model;

public class Prestation {
    private final int id;
    private String name;
    private float price;

    public Prestation(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
