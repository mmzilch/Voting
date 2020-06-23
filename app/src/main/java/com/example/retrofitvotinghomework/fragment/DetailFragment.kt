package com.example.retrofitvotinghomework.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.retrofitvotinghomework.R
import com.example.retrofitvotinghomework.api.Api
import com.example.retrofitvotinghomework.model.VoteResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private val votingApi: Api = Api()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = arguments.let { DetailFragmentArgs.fromBundle(it!!) }

        tvId.text = data.id
        tvName.text =  data.name
        tvVote.text = "Rating: " + data.votecount
        Picasso.get()
            .load(data.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)
        tvStatus.text = data.votestatus

        btnVote.setOnClickListener {
            var code = editCode.text.toString()

            if (TextUtils.isEmpty(code)) {
                editCode.setError("Require")
            }

            if (!code.isEmpty()) {
                voteKing(data.id, code)
            }
        }
    }

    private fun voteKing(id: String, code: String) {

        var apiCall = votingApi.queenVote(id,code)
        Log.d ("id>>>","$id $code")

        apiCall.enqueue(
            object : Callback<VoteResponse> {
                override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<VoteResponse>,
                    response: Response<VoteResponse>
                ) {
                     Toast.makeText(context,response.body()?.message,Toast.LENGTH_LONG).show()
                    Log.d("Message>>>>>", response.body()?.message)
                }

            }
        )
    }

}