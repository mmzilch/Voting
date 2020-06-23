package com.example.retrofitvotinghomework.api

import com.example.retrofitvotinghomework.model.King
import com.example.retrofitvotinghomework.model.VoteResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api{
    private val apiInterface : ApiInterface

    companion object {
        const val Base_URL="https://ucsmonywaonlinevote.000webhostapp.com/api/"
    }

    init {//when create class obj and it create retrofits
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface=retrofit.create(ApiInterface::class.java)
    }

    fun getKing( ): Call<King>
    {
        return apiInterface.getKing()
    }

    fun getQueen() : Call<King>
    {
        return apiInterface.getQueen()
    }

    fun queenVote(code: String, id :String):Call<VoteResponse>{
        return apiInterface.voteKing(code,id)
    }

    /*fun getKingDetails(detailList : KingItem): Call<KingItem>
    {
        return apiInterface.getKingDetails(detailList)
    }*/
}