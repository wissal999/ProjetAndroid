package com.example.mpdamproject2021;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ApiHandler {

    @FormUrlEncoded
    @POST("MpdamProject2021/insertUser.php")
    Call<User> insertUser(@Field("fullname") String fullname,
                          @Field("username") String username,
                          @Field ("phone") String phone,
                          @Field("email") String email,
                          @Field ("password") String password

    );

    @FormUrlEncoded
    @POST("MpdamProject2021/LoginUser.php")
    Call<User> loginUser(@Field("username") String username,
                         @Field ("password") String password
    );
}
