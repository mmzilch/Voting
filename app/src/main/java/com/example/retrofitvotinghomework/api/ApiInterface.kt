package com.example.retrofitvotinghomework.api

import com.example.retrofitvotinghomework.model.King
import com.example.retrofitvotinghomework.model.VoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("king")
    fun getKing(): Call<King>

    @GET("queen")
    fun getQueen(): Call<King>

    @POST("kingvote")
    fun voteKing(
        @Query("code")code : String,
        @Query("king_id") kingId: String
    ) : Call<VoteResponse>

}