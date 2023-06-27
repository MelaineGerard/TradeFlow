package fr.melaine.gerard.tradeflow.service;

import java.io.IOException;

import com.google.gson.JsonObject;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.model.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService {
    private static final MediaType JSON
    = MediaType.get("application/json; charset=utf-8");

    public static boolean login(String username, String password){
        String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
              
        RequestBody body = RequestBody.create(json, JSON);      

        Request request = new Request.Builder()
            .post(body)
            .url("http://localhost:8000/api/users/login")
            .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonObject = TradeFlow.getGson().fromJson(responseBody, JsonObject.class);
                boolean isLogged = !jsonObject.get("user").isJsonNull();

                return isLogged;
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    public static boolean createUser(User user) {
        String json = TradeFlow.getGson().toJson(user);
        RequestBody body = RequestBody.create(json, JSON);      

        Request request = new Request.Builder()
            .post(body)
            .url("http://localhost:8000/api/users/create")
            .build();

        try (Response response = getHttpClient().newCall(request).execute()) {
            return response.isSuccessful() && response.code() == 201;
        } catch (IOException e) {
            return false;
        }
    }
}
