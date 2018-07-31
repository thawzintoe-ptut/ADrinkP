package com.example.ptut.adrinkp.models.base

import android.content.Context
import com.example.ptut.adrinkp.network.ADrinkPService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel protected constructor() {

    protected var mTheApi: ADrinkPService
//    protected var mTheDB: AppDatabase

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        mTheApi = retrofit.create<ADrinkPService>(ADrinkPService::class.java)
//        mTheDB = AppDatabase.getDatabase(context)
    }
}