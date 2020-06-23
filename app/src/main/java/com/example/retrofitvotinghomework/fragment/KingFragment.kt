package com.example.retrofitvotinghomework.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitvotinghomework.R
import com.example.retrofitvotinghomework.adapter.VotinAdapter
import com.example.retrofitvotinghomework.model.KingItem
import com.example.retrofitvotinghomework.viewmodel.KingViewModel
import kotlinx.android.synthetic.main.fragment_king.*

class KingFragment : Fragment() ,VotinAdapter.ClickListener{

    private lateinit var kingViewModel: KingViewModel
    private lateinit var votingAdapter: VotinAdapter
    private lateinit var viewManager  : RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_king, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(activity,2)
        votingAdapter = VotinAdapter()//no need to add constructor cause of we defined array list
        rcyKing.apply {
            adapter= votingAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        kingViewModel = ViewModelProvider(this).get(KingViewModel::class.java)//create VM object
        //movieViewModel.loadResult()
        kingViewModel.topResults.observe(
            viewLifecycleOwner, Observer {
                rcyKing.visibility = View.VISIBLE
                votingAdapter.updateList(it)

                Log.d("UPdateList>>>",it.toString())
                votingAdapter.setClickListener(this)
            }
        )

        kingViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it) {
                    rcyKing.visibility = View.GONE
                } else {
                    rcyKing.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        kingViewModel.loadResult()//data loading and get data
    }

    override fun onClick(detailList : KingItem) {
        var action = KingFragmentDirections.actionNavGalleryToDetailFragment(
            detailList.id,
            detailList.name,
            detailList.vote_count,
            detailList.img_url,
            detailList.vote_time_status.toString())
        findNavController().navigate(action)
    }
}