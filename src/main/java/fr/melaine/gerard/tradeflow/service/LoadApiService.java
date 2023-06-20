package fr.melaine.gerard.tradeflow.service;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.PaymentMethod;
import fr.melaine.gerard.tradeflow.model.User;

public class LoadApiService {

    private static LoadApiService INSTANCE;

    public static LoadApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadApiService();
        }
        return INSTANCE;
    }

    public void loadUsers() {
        // TODO: Faire un call à l'api pour récupérer les utilisateurs + retirer mot de passe en clair
        TradeFlow.addUser(
            new User(
                1,
                "Melaine",
                "melaine",
                "monSuperMDP",
                "admin"
            )
        );
    }

    public void loadClients() {
        // TODO: Faire un call à l'api pour récupérer les clients

    }

    public void loadPrestations() {
        // TODO: Faire un call à l'api pour récupérer les prestations

    }

    public void loadPaymentMethods() {
        // TODO: Faire un call à l'api pour récupérer les méthodes de paiement
        TradeFlow.addPaymentMethod(
            new PaymentMethod(1, "Carte bancaire")
        );
        TradeFlow.addPaymentMethod(
            new PaymentMethod(2, "Espèces")
        );
        TradeFlow.addPaymentMethod(
            new PaymentMethod(3, "Chèque")
        );
    }

    public void loadSellReports() {
        // TODO: Faire un call à l'api pour récupérer les rapports de vente
    }

    public void loadTransactions() {
        // TODO: Faire un call à l'api pour récupérer les transactions
    }
}
