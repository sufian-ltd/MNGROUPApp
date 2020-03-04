package com.example.abusufian.mngroupapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getProductName.php")
    Call<List<Product>> getProductName();

    @GET("addProduct.php")
    Call<ServerResponse> addProduct(@Query("name") String name,@Query("description") String description,
        @Query("debit") int debit,@Query("credit") int credit,@Query("date") String date);

    @GET("getProducts.php")
    Call<List<Product>> getProducts();

    @GET("getProduct.php")
    Call<List<Product>> getProduct(@Query("name") String name);

    @GET("getStock.php")
    Call<List<Product>> getStock();

    @GET("getCustomerName.php")
    Call<List<Product>> getCustomerName();

    @GET("addCustomer.php")
    Call<ServerResponse> addCustomer(@Query("name") String name,@Query("description") String description,
                                    @Query("debit") int debit,@Query("credit") int credit,@Query("date") String date);

    @GET("getCustomer.php")
    Call<List<Product>> getCustomer(@Query("name") String name);

    @GET("getCustomers.php")
    Call<List<Product>> getCustomers();

    @GET("getTotalCustomer.php")
    Call<List<Product>> getTotalCustomer();

    @GET("addMarket.php")
    Call<ServerResponse> addMarket(@Query("name") String name,@Query("loc") String loc,
        @Query("contact") String contact,@Query("description") String description,
        @Query("paid") int paid,@Query("due") int due,@Query("date") String date);

    @GET("getMarketName.php")
    Call<List<Product>> getMarketName();

    @GET("saveTransaction.php")
    Call<ServerResponse> saveTransaction(@Query("name") String name,@Query("description") String description,
            @Query("paid") int paid,@Query("due") int due,@Query("date") String date);

    @GET("getMarketTransaction.php")
    Call<List<Product>> getMarketTransaction(@Query("name") String name);

    @GET("getMarket.php")
    Call<List<Product>> getMarket();

    @GET("login.php")
    Call<ServerResponse> login(@Query("username") String username,@Query("password") String password);

    @GET("addAppUser.php")
    Call<ServerResponse> addAppUser(@Query("fullName") String fullName,@Query("username")
            String username,@Query("password") String password,
            @Query("user") String user);

    @GET("getAppUser.php")
    Call<List<Product>> getAppUser();


    @GET("deleteStock.php")
    Call<ServerResponse> deleteStock(@Query("name") String name);
    @GET("deleteCustomer.php")
    Call<ServerResponse> deleteCustomer(@Query("name") String name);
    @GET("deleteMarket.php")
    Call<ServerResponse> deleteMarket(@Query("name") String name);
    @GET("deleteProduct.php")
    Call<ServerResponse> deleteProduct(@Query("id") int id);
    @GET("deleteCustomerTransaction.php")
    Call<ServerResponse> deleteCustomerTransaction(@Query("id") int id);
    @GET("deleteMarketTransaction.php")
    Call<ServerResponse> deleteMarketTransaction(@Query("id") int id);
    @GET("deleteUser.php")
    Call<ServerResponse> deleteUser(@Query("id") int id);
    @GET("updateAppUser.php")
    Call<ServerResponse> updateAppUser(@Query("fullName") String fullName,@Query("username")
            String username,@Query("password") String password,
                                    @Query("id") int id);
}
