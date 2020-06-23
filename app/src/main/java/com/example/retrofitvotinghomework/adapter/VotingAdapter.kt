package com.example.retrofitvotinghomework.adapter

import android.security.keystore.KeyInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitvotinghomework.R
import com.example.retrofitvotinghomework.model.KingItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_king.view.*

class VotinAdapter: RecyclerView.Adapter<VotinAdapter.MovieViewHolder>(){

    var mClickListener: ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    var resultList: List<KingItem> = listOf()

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        private lateinit var result: KingItem

        init {
            itemView.setOnClickListener(this)//initialize onClick fun:s and/ when start create obj,it works
        }

        fun bindMovie(result:KingItem){
            this.result = result
            Picasso.get().load(result.img_url)
                .placeholder(R.drawable.ic_launcher_background).into(itemView.imageKing)//place holder is to show temp image when loading
            itemView.tvKingId.text = result.id
            itemView.tvKingName.text  = result.name

            Log.d("ID>>>",result.id)
        }

        override fun onClick(view: View?) {
            mClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_king,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
        Log.d("new>>>>>",resultList.size.toString())
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(resultList[position])
    }

    fun updateList(result : List<KingItem>)
    {
        this.resultList = result
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(detailList : KingItem )
    }

}