package fr.melaine.gerard.tradeflow.service;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.PaymentMethod;
import fr.melaine.gerard.tradeflow.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadApiService {

    private static LoadApiService INSTANCE;

    public static LoadApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadApiService();
        }
        return INSTANCE;
    }

    public void loadUsers() throws IOException {
        Request request = new Request.Builder()
            .url("https://localhost:8000/api/users")
            .build();

        Response response = getHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JsonArray usersJsonArray = TradeFlow.getGson().fromJson(responseBody, JsonArray.class);
            TradeFlow.getUsers().clear();

            for (JsonElement jsonElement : usersJsonArray) {
                JsonObject userJsonObject = jsonElement.getAsJsonObject();
                int id = userJsonObject.get("id").getAsInt();
                String name = userJsonObject.get("name").getAsString();
                String username = userJsonObject.get("username").getAsString();
                String password = userJsonObject.get("password").getAsString();
                String role = userJsonObject.get("roles").getAsJsonArray().get(0).getAsString();

                User user = new User(
                    id,
                    name,
                    username,
                    password,
                    role
                );

                TradeFlow.addUser(user);
            }
        }
        
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

    private OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}
