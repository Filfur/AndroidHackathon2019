package com.noteamname.androidhackathon.toiletRoll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.toiletRoll.interfaces.ItemTouchHelperAdapter
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import kotlinx.android.synthetic.main.fragment_toilet_roll_item.view.*
import java.util.*

class ToiletRollAdapter(
    val listener: ToiletRollListener
)
    : RecyclerView.Adapter<ToiletRollAdapter.RollViewHolder>(), ItemTouchHelperAdapter {

    var items: LinkedList<RollPiece> = LinkedList()

    fun updateItems(newItems: List<RollPiece>) {
        items = LinkedList(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<RollPiece>) {
        newItems.forEach { item ->
            items.addFirst(item)
        }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_toilet_roll_item, parent, false)
        return RollViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: RollViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            view.tag = item

            text.text = item.text
            view.setOnClickListener { view ->
//                listener.onSelected(view.tag as RollPeace)
            }
        }
    }

    class RollViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.item_text
    }

    interface ToiletRollListener {
        fun onItemDismissed()
    }

    override fun onItemDismiss(position: Int) {
        (position downTo 0).forEach { index ->
            items.removeAt(index)
            notifyItemRemoved(index)
            listener.onItemDismissed()
        }
    }
}