package com.capestone.shakeitup.service

import com.capestone.shakeitup.data.CocktailDetailList
import com.capestone.shakeitup.data.CocktailList
import com.capestone.shakeitup.utility.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private val client: OkHttpClient? = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .client(client)
    .build()

interface CocktailApiService {
    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholicCocktails(): CocktailList

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicCocktails(): CocktailList

    @GET("lookup.php")
    suspend fun getCocktailDetails(
        @Query("i") id: String
    ): CocktailDetailList

    @GET("random.php")
    suspend fun getRandomCocktail(): CocktailDetailList
}

object CocktailApi {
    val retrofitService: CocktailApiService by lazy {
        retrofit.create(CocktailApiService::class.java)
    }
}