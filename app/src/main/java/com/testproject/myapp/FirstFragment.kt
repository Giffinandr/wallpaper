package com.testproject.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapp.listview.CategoryListAdapter
import com.testproject.myapp.viewmodel.Model

class FirstFragment : Fragment() {
    private val model by viewModels<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val adapter = CategoryListAdapter()
        model.getListCategory().value?.let { adapter.subList(it) }

        val recyclerView: RecyclerView = view.findViewById(R.id.category_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        Toast.makeText(view.context,
            if (model.isConnect()) "connected" else "disconnected",
            Toast.LENGTH_SHORT).show()

        return view
    }

}