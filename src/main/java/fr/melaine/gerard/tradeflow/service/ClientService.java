package fr.melaine.gerard.tradeflow.service;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.Client;
import okhttp3.*;

import java.io.IOException;

public class ClientService {
    private static final MediaType JSON
    = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    public static boolean createClient(Client client) {
        String json = TradeFlow.getGson().toJson(client);
        RequestBody body = RequestBody.create(json, JSON);      

        Request request = new Request.Builder()
            .post(body)
            .url("http://localhost:8000/api/clients/create")
            .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            return response.isSuccessful() && response.code() == 201;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deleteClient(int clientId) {

        Request request = new Request.Builder()
                .delete()
                .url("http://localhost:8000/api/clients/" + clientId)
                .build();

        try {
            getHttpClient().newCall(request).execute();
        } catch (IOException ignored) {

        }
    }

    public static boolean editClient(Client user) {
        String json = TradeFlow.getGson().toJson(user);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .put(body)
                .url("http://localhost:8000/api/clients/edit/" + user.getId())
                .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            return response.isSuccessful() && response.code() == 201;
        } catch (IOException e) {
            return false;
        }
    }
}
