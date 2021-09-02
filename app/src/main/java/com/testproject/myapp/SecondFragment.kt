package com.testproject.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testproject.myapp.listview.ImageRecyclerAdapter
import com.testproject.myapp.viewmodel.Model

class SecondFragment : Fragment() {
    private val model by viewModels<Model>()
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val string = args.selectedList

        model.setPixabayList(string)

        val adapter = ImageRecyclerAdapter()
        val recycler: RecyclerView = view.findViewById(R.id.selected_list)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(context, 3)

        model.getList().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return view
    }

}