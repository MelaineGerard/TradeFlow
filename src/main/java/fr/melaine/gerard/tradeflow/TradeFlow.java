package fr.melaine.gerard.tradeflow;

import java.util.ArrayList;
import java.util.List;

import fr.melaine.gerard.tradeflow.model.Client;
import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.view.LoginPageView;

public class TradeFlow {
    private static User user;
    private static List<User> users;
    private static List<Client> clients;

    public static void main(String[] args) {
        user = null;
        users = new ArrayList<>();
        clients = new ArrayList<>();

        new LoginPageView();
    }

    public static User getUser() {
        return user;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUser(User user) {
        TradeFlow.user = user;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static void addClient(Client client) {
        clients.add(client);
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }
}