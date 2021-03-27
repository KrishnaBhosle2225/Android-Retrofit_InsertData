package com.krishna.retrofit_insertdata;

import android.util.JsonReader;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface myapi {

    @FormUrlEncoded
    @POST("Retrofit_Login.php")

    Call<Model> addData(@Field("name")String name, @Field("username") String username, @Field("password") String password);

}
