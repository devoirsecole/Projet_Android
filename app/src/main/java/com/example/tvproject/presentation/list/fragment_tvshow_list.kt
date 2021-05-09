package com.example.tvproject.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvproject.R
import com.example.tvproject.presentation.Singletons
import com.example.tvproject.presentation.api.TVShowAPI
import com.example.tvproject.presentation.api.TVShowResponseList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class fragment_tvshow_list : Fragment() {

    private lateinit var recyclerview : RecyclerView
    private val adapter = TVShowsAdapter(listOf(), ::onClickedTVShow)

    private val viewModel: TVShowListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.tvshow_recyclerview)



        recyclerview.apply {
            layoutManager =  LinearLayoutManager(context)
            adapter = this@fragment_tvshow_list.adapter
        }

        viewModel.TVShowList.observe(viewLifecycleOwner, Observer { list -> adapter.updateList(list) })

    }

    private fun onClickedTVShow(tvshow: TVShow) {
        findNavController().navigate(R.id.navigateToTVShowDetails, bundleOf("tvshowid" to tvshow.id))

    }
}