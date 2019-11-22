package com.noteamname.androidhackathon.toiletRoll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.toiletRoll.models.RollPieceHelper
import kotlinx.android.synthetic.main.fragment_toilet_roll.*
import org.koin.android.viewmodel.ext.android.viewModel


class ToiletRollFragment : Fragment() {

    private var pageCount = 0

    val viewModel: ToiletRollViewModel by viewModel()

    private val adapter: ToiletRollAdapter by lazy {
        ToiletRollAdapter(RollPieceHelper.getPeaces())
    }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val lastVisibleItemPosition: Int = layoutManager.findLastVisibleItemPosition()
            if (lastVisibleItemPosition == adapter.itemCount - 1) {
                if (!loading && !isLastPage) {
                    viewModel.getRollPieces(++pageCount)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_toilet_roll, container, false)

        view.findViewById<RecyclerView>(R.id.toilet_roll).apply {
            layoutManager = this@ToiletRollFragment.layoutManager
            adapter = this@ToiletRollFragment.adapter
            addOnScrollListener(this@ToiletRollFragment.scrollListener)
            setHasFixedSize(true)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toilet_roll.smoothScrollToPosition(0)

    }
}