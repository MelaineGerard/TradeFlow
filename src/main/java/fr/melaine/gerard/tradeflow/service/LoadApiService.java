package fr.melaine.gerard.tradeflow.service;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.Client;
import fr.melaine.gerard.tradeflow.model.PaymentMethod;
import fr.melaine.gerard.tradeflow.model.Prestation;
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
            .url("http://localhost:8000/api/users")
            .build();

        Response response = getHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            String responseBody = response.body() != null ? response.body().string() : null;
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

    public void loadClients() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8000/api/clients")
                .build();

        Response response = getHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            String responseBody = response.body() != null ? response.body().string() : null;
            JsonArray clientsJsonArray = TradeFlow.getGson().fromJson(responseBody, JsonArray.class);
            TradeFlow.getClients().clear();

            for (JsonElement jsonElement : clientsJsonArray) {
                JsonObject userJsonObject = jsonElement.getAsJsonObject();
                int id = userJsonObject.get("id").getAsInt();
                String name = userJsonObject.get("name").getAsString();
                String address = userJsonObject.get("address").getAsString();
                String city = userJsonObject.get("city").getAsString();

                Client client = new Client(
                        id,
                        name,
                        address,
                        city
                );

                TradeFlow.addClient(client);
            }
        }

    }

    public void loadPrestations() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8000/api/prestations")
                .build();

        Response response = getHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            String responseBody = response.body() != null ? response.body().string() : null;
            JsonArray clientsJsonArray = TradeFlow.getGson().fromJson(responseBody, JsonArray.class);
            TradeFlow.getPrestations().clear();

            for (JsonElement jsonElement : clientsJsonArray) {
                JsonObject userJsonObject = jsonElement.getAsJsonObject();
                int id = userJsonObject.get("id").getAsInt();
                String name = userJsonObject.get("name").getAsString();
                float price = userJsonObject.get("price").getAsFloat();

                Prestation prestation = new Prestation(
                        id,
                        name,
                        price
                );

                TradeFlow.addPrestation(prestation);
            }
        }
    }

    public void loadPaymentMethods() {
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
    }

    public void loadTransactions() {
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}
