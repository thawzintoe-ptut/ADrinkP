package com.example.ptut.adrinkp.network


import com.example.ptut.adrinkp.network.response.*
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Query

interface ADrinkPService {

    //Drinks List
    @GET("list.php")
    fun getCategoryList(@Query("c")list:String):Observable<GetCategoryResponse>
    @GET("list.php")
    fun getGlassList(@Query("g")list:String):Observable<GetGlassResponse>
    @GET("list.php")
    fun getIngredientList(@Query("i")list:String):Observable<GetIngredientResponse>
    @GET("list.php")
    fun getAlcoholList(@Query("a")list:String):Observable<GetAlcoholResponse>

    //Drinks Filter
    @GET("filter.php")
    fun getFilterItemList(@Query("c")list: String):Observable<GetCategoryFilterResponse>
    @GET("filter.php")
    fun getFilterGlassList(@Query("g")list: String):Observable<GetCategoryFilterResponse>
    @GET("filter.php")
    fun getFilterAlcoholList(@Query("a")list: String):Observable<GetCategoryFilterResponse>
    @GET("filter.php")
    fun getFilterIngredientList(@Query("i")list: String):Observable<GetCategoryFilterResponse>

    //Search Ingredients
    @GET("search.php?")
    fun getCocktailByName(@Query("s")name: String):Observable<GetCocktailSearchResponse>
    @GET("search.php?")
    fun getIngredientByName(@Query("i")name: String):Observable<GetIngredientSearchResponse>
}