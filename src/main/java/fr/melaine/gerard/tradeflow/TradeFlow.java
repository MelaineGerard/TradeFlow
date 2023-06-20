package fr.melaine.gerard.tradeflow;

import java.util.ArrayList;
import java.util.List;

import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.view.LoginPageView;

public class TradeFlow {
    private static User user;
    private static List<User> users;

    public static void main(String[] args) {
        users = new ArrayList<>();
        user = null;
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
}