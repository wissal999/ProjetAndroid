package com.example.mpdamproject2021;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

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
    @FormUrlEncoded
    @POST("MpdamProject2021/getProducts.php")
    Call<List<Product>> getProducts(@Field("categoryProduct") String categoryProduct);

    @FormUrlEncoded
    @POST("MpdamProject2021/scanProduct.php")
    Call<Product> scanProduct(@Field("barcodeProduct") String barcodeProduct);
}
