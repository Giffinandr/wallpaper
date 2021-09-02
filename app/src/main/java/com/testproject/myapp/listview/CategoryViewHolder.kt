package com.testproject.myapp.listview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapp.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val text = itemView.findViewById<TextView>(R.id.category_text)

    fun setText(string: String) {
        text.text= string
    }
}