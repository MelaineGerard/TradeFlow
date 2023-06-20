package fr.melaine.gerard.tradeflow;

import java.util.ArrayList;
import java.util.List;

import fr.melaine.gerard.tradeflow.model.Client;
import fr.melaine.gerard.tradeflow.model.PaymentMethod;
import fr.melaine.gerard.tradeflow.model.Prestation;
import fr.melaine.gerard.tradeflow.model.SellReport;
import fr.melaine.gerard.tradeflow.model.Transaction;
import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.service.LoadApiService;
import fr.melaine.gerard.tradeflow.view.LoginPageView;

public class TradeFlow {
    private static User user;
    private static List<User> users;
    private static List<Client> clients;
    private static List<Prestation> prestations;
    private static List<PaymentMethod> paymentMethods;
    private static List<SellReport> sellReports;
    private static List<Transaction> transactions;

    public static void main(String[] args) {
        user = null;
        users = new ArrayList<>();
        clients = new ArrayList<>();
        prestations = new ArrayList<>();
        paymentMethods = new ArrayList<>();
        sellReports = new ArrayList<>();
        transactions = new ArrayList<>();

        LoadApiService.getInstance().loadUsers();
        LoadApiService.getInstance().loadClients();
        LoadApiService.getInstance().loadPrestations();
        LoadApiService.getInstance().loadPaymentMethods();
        LoadApiService.getInstance().loadTransactions();
        LoadApiService.getInstance().loadSellReports();
    

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

    public static List<Prestation> getPrestations() {
        return prestations;
    }

    public static void addPrestation(Prestation prestation) {
        prestations.add(prestation);
    }

    public static void removePrestation(Prestation prestation) {
        prestations.remove(prestation);
    }

    public static List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public static void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }

    public static void removePaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.remove(paymentMethod);
    }

    public static List<SellReport> getSellReports() {
        return sellReports;
    }

    public static void addSellReport(SellReport sellReport) {
        sellReports.add(sellReport);
    }

    public static void removeSellReport(SellReport sellReport) {
        sellReports.remove(sellReport);
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public static void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }
}