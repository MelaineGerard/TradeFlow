package fr.melaine.gerard.tradeflow;

import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.view.LoginPageView;

public class TradeFlow {
    private static User user;

    public static void main(String[] args) {
        user = null;
        new LoginPageView();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        TradeFlow.user = user;
    }
}