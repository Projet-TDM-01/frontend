package com.example.projet_tdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.ViewModels.SearchViewModel
import com.example.projet_tdm.adapter.SearchAdapter
import java.util.*


class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.searchRecyclerView) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        val searchBar = view.findViewById<SearchView>(R.id.search_parking)

        val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        searchBar.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                // Override onQueryTextSubmit method
                // which is call
                // when submitquery is searched
                override fun onQueryTextSubmit(query: String): Boolean {
                    // If the list contains the search query
                    // than filter the adapter
                    // using the filter method
                    // with the query as its argument
                    searchViewModel.searchByNom(query.trim().lowercase(Locale.getDefault()))
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        searchViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                view.findViewById<ProgressBar>(R.id.search_progress).visibility = VISIBLE
            } else {
                view.findViewById<ProgressBar>(R.id.search_progress).visibility = GONE
            }
        }
        searchViewModel.searchList.observe(viewLifecycleOwner) {
            searchList ->
            recyclerView.layoutManager = layoutManager
            val adapter = SearchAdapter(view.context, searchList)
            recyclerView.adapter = adapter
        }
    }
}