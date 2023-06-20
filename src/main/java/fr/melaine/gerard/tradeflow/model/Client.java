package fr.melaine.gerard.tradeflow.model;

public class Client {
    private String name;
    private String address;
    private String city;

    public Client(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }
}
