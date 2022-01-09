package org.cardna.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.cardna.R

class MyAdapter(private val bgColors: ArrayList<Int>) :
    RecyclerView.Adapter<MyAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pageName: TextView = itemView.findViewById(R.id.tv_test)

        fun bind(@ColorRes bgColor: Int, position: Int) {
            pageName.text = "Page ${position + 1}"
            pageName.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_card,
            parent,
            false
        )
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(bgColors[position], position)
    }

    override fun getItemCount(): Int = bgColors.size
}
