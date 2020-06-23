package com.example.retrofitvotinghomework.viewmodel

import android.security.keystore.KeyInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitvotinghomework.api.Api
import com.example.retrofitvotinghomework.model.King
import com.example.retrofitvotinghomework.model.KingItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KingViewModel : ViewModel() {

    var topResults: MutableLiveData<List<KingItem>> = MutableLiveData()
    var detailResult: MutableLiveData<KingItem> = MutableLiveData()
    var resultLoadError : MutableLiveData<Boolean> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

    fun getResults(apiKey:String): LiveData<List<KingItem>> = topResults
    fun getDetails():LiveData<KingItem> = detailResult
    fun getError() : LiveData<Boolean> = resultLoadError
    fun getLoading() : LiveData<Boolean> = loading

    private val votingApi: Api = Api()//obj//movie api => getNews()

    fun loadResult() {
        loading.value = true
        val apiCall = votingApi.getKing()
        apiCall.enqueue(object : Callback<King> {

            override fun onFailure(call: Call<King>, t: Throwable) {
                resultLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<King>, response: Response<King>) {
                response.isSuccessful.let {
                    loading.value = false
                    val resultList:List<KingItem> = response.body()?: emptyList()
                    topResults.value = resultList
                }
            }
        })
    }

    fun loadQueenResult() {
        loading.value = true
        val apiCall = votingApi.getQueen()
        apiCall.enqueue(object : Callback<King> {

            override fun onFailure(call: Call<King>, t: Throwable) {
                resultLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<King>, response: Response<King>) {
                response.isSuccessful.let {
                    loading.value = false
                    val resultList:List<KingItem> = response.body()?: emptyList()
                    topResults.value = resultList

                    Log.d("QueenResultList>>>>",resultList.toString())

                }
            }
        })
    }
}