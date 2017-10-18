package com.davidsuescun.mybizizaragoza.mybizizaragoza.utils;

import com.davidsuescun.mybizizaragoza.mybizizaragoza.data.Data;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICommunicator implements Requests {
    private static final String API_HEADER_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String API_HEADER_APP_JSON = "application/json";
    private static final String grantType = "password";

    @Override
    public Call<ResponseBody> plazaMozart() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.dndzgz.com/")
                .build();

        Requests requests = retrofit.create(Requests.class);

        Call<ResponseBody> call = requests.plazaMozart();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String plazaMozart = response.body().string();

                    int posicionBicis = plazaMozart.indexOf(" bicicletas");
                    posicionBicis = posicionBicis - 2;
                    if(posicionBicis>0) {
                        Data.setBicisPlazaMozart(plazaMozart.substring(posicionBicis, posicionBicis + 13));
                    }

                    int posicionHuecos = plazaMozart.indexOf(" aparcamientos");
                    posicionHuecos = posicionHuecos - 2;
                    if(posicionHuecos>0) {
                        Data.setHuecosPlazaMozart(plazaMozart.substring(posicionHuecos, posicionHuecos + 16));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return null;
    }

    @Override
    public Call<ResponseBody> pedroJSoler() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.dndzgz.com/")
                .build();

        final Requests requests = retrofit.create(Requests.class);

        Call<ResponseBody> call = requests.pedroJSoler();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String pedroJSoler = response.body().string();

                    int posicionBicis = pedroJSoler.indexOf(" bicicletas");
                    posicionBicis = posicionBicis - 4;
                    if(posicionBicis>0) {
                        Data.setBicisPedroJSoler(pedroJSoler.replaceAll("\"", "").substring(posicionBicis, posicionBicis + 12));
                    }

                    int posicionHuecos = pedroJSoler.indexOf(" aparcamientos");
                    posicionHuecos = posicionHuecos - 7;
                    if(posicionHuecos>0) {
                        Data.setHuecosPedroJSoler(pedroJSoler.replaceAll("\"", "").substring(posicionHuecos, posicionHuecos + 16));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return null;
    }
}
