package com.testproject.myapp.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapp.FirstFragmentDirections
import com.testproject.myapp.R
import com.testproject.myapp.network.ConnectNetwork

class CategoryListAdapter: RecyclerView.Adapter<CategoryViewHolder>() {
    private val listKey = ArrayList<String>()
    private val listValue = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = listKey[position]
        holder.setText(category)
        holder.itemView.setOnClickListener {
            if (ConnectNetwork.isConnect()) {
                val query = listValue[position]
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(query)
                it.findNavController().navigate(action)
            } else {
                Toast.makeText(it.context,
                    "DISCONNECT\n check internet connection",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listKey.size
    }

    fun subList(list: Map<String, String>){
        listKey.addAll(list.keys)
        listValue.addAll(list.values)
    }
}