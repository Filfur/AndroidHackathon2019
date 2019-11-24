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
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ToiletRollFragment : Fragment(), ToiletRollAdapter.ToiletRollListener {

    private val viewModel: ToiletRollViewModel by sharedViewModel()

    private val adapter: ToiletRollAdapter by lazy {
        ToiletRollAdapter(this)
    }

    private var range: Int? = null

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            recalculateRollView(recyclerView)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            recalculateRollView(recyclerView)
        }
    }

    fun recalculateRollView(recyclerView: RecyclerView) {
        val offset = recyclerView.computeVerticalScrollOffset()
        val extent = recyclerView.computeVerticalScrollExtent()
        val range = range ?: recyclerView.computeVerticalScrollRange()

        val percentage = 1 - offset / (range.toFloat() - extent)

        roll_view.progress = percentage
    }

    private val rollPiecesObserver = Observer<List<RollPiece>> { pieces ->
        adapter.addItems(pieces.reversed())
        toilet_roll.post {
            toilet_roll.smoothScrollToPosition(0)
        }
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
        viewModel.livePieces.observe(viewLifecycleOwner, rollPiecesObserver)
    }

    override fun onItemDismissed() {
        range = range ?: toilet_roll.computeVerticalScrollRange()
        recalculateRollView(toilet_roll)
//        toilet_roll.smoothScrollToPosition(0)
    }
}