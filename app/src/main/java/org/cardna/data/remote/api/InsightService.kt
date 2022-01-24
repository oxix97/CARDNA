package org.cardna.data.remote.api

import org.cardna.data.remote.model.insight.ResponseInsightData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface InsightService {
    @GET("insight")
    suspend fun getInsight(): ResponseInsightData
}