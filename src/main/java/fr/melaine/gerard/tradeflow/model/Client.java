package fr.melaine.gerard.tradeflow.model;

public class Client {
    private int id;
    private String name;
    private final String address;
    private final String city;

    public Client(int id, String name, String address, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
