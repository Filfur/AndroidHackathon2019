package com.noteamname.androidhackathon.toiletRoll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import com.noteamname.androidhackathon.toiletRoll.utils.SimpleItemTouchHelperCallback
import com.noteamname.androidhackathon.toiletRoll.viewModels.ToiletRollViewModel
import kotlinx.android.synthetic.main.fragment_toilet_roll.*
import org.koin.android.viewmodel.ext.android.viewModel


class ToiletRollFragment : Fragment() {

    val viewModel: ToiletRollViewModel by viewModel()

    private val adapter: ToiletRollAdapter by lazy {
        ToiletRollAdapter()
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val lastVisibleItemPosition: Int = (toilet_roll.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (lastVisibleItemPosition == adapter.itemCount - 1) {
//                viewModel.fetchRollPieces()
            }
            val offset = recyclerView.computeVerticalScrollOffset()
//            val extent = recyclerView.computeVerticalScrollExtent()
            val height = recyclerView.height
            val range = recyclerView.computeVerticalScrollRange()

            val percentage = 1 - offset / (range.toFloat() - height)

            roll_view.progress = percentage
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val offset = recyclerView.computeVerticalScrollOffset()
//            val extent = recyclerView.computeVerticalScrollExtent()
            val height = recyclerView.height
            val range = recyclerView.computeVerticalScrollRange()

            val percentage = 1 - offset / (range.toFloat() - height)

            roll_view.progress = percentage
        }
    }

    private val rollPiecesObserver = Observer<List<RollPiece>> { pieces ->
        adapter.addItems(pieces.reversed())
        toilet_roll.smoothScrollToPosition(0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_toilet_roll, container, false)

        view.findViewById<RecyclerView>(R.id.toilet_roll).apply {
            layoutManager =  LinearLayoutManager(requireContext()).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = this@ToiletRollFragment.adapter
            addOnScrollListener(this@ToiletRollFragment.scrollListener)
            setHasFixedSize(true)
            val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(this@ToiletRollFragment.adapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(this)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toilet_roll.smoothScrollToPosition(0)
        viewModel.livePieces.observe(viewLifecycleOwner, rollPiecesObserver)
    }
}