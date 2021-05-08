package com.example.tvproject.presentation

import com.example.tvproject.presentation.TVShowApplication.Companion.context
import com.example.tvproject.presentation.api.TVShowAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File

class Singletons{

    companion object{

            var cache =Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

            val okhttpclient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()

            val tvShowAPI: TVShowAPI = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/tv/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpclient)
                    .build()
                    .create(TVShowAPI::class.java)

    }

}

