package com.example.recipe_firebasestore.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getRecipies(): Call<List<recipe.dat>>


    @Headers("Content-Type: application/json")
    @POST("/recipes/")
    fun addRecipie(@Body userData: recipe.dat): Call<recipe.dat>


}
