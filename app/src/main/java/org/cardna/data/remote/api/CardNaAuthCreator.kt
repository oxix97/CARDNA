package org.cardna.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CardNaAuthCreator {
    private const val BASE_URL = "https://asia-northeast3-cardna-29f5b.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cardNaAuthService: CardNaAuthService = retrofit.create(CardNaAuthService::class.java)
}