package fr.melaine.gerard.tradeflow.service;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.Prestation;
import okhttp3.*;

import java.io.IOException;

public class PrestationService {
    private static final MediaType JSON
    = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    public static boolean createPrestation(Prestation prestation) {
        String json = TradeFlow.getGson().toJson(prestation);
        RequestBody body = RequestBody.create(json, JSON);      

        Request request = new Request.Builder()
            .post(body)
            .url("http://localhost:8000/api/prestations/create")
            .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            return response.isSuccessful() && response.code() == 201;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deletePrestation(int prestationId) {

        Request request = new Request.Builder()
                .delete()
                .url("http://localhost:8000/api/prestations/" + prestationId)
                .build();

        try {
            getHttpClient().newCall(request).execute();
        } catch (IOException ignored) {

        }
    }

    public static boolean editPrestation(Prestation prestation) {
        String json = TradeFlow.getGson().toJson(prestation);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .put(body)
                .url("http://localhost:8000/api/prestations/edit/" + prestation.getId())
                .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            return response.isSuccessful() && response.code() == 201;
        } catch (IOException e) {
            return false;
        }
    }
}
