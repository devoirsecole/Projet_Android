package com.example.tvproject.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TVShowAPI {
    companion object{
        val APIKEY = "dd2d91f98be44a04d0ba7a02272bb43d"
    }
    @GET("top_rated")
    fun getTopTVShow( @Query("api_key") key : String= APIKEY) : Call<TVShowResponseList>

    @GET("{id}")
    fun getTVShowDetails(@Path("id") id:Int, @Query("api_key") key: String= APIKEY) : Call<TVShowDetailsResponse>

}