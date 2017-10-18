package com.davidsuescun.mybizizaragoza.mybizizaragoza.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

interface Requests {
    @GET("point?service=bizi&id=59")
    Call<ResponseBody> plazaMozart();

    @GET("point?service=bizi&id=102")
    Call<ResponseBody> pedroJSoler();
}