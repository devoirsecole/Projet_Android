package com.example.tvproject.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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

        Singletons.tvShowAPI.getTopTVShow("dd2d91f98be44a04d0ba7a02272bb43d").enqueue(object: Callback<TVShowResponseList>{
            override fun onFailure(call: Call<TVShowResponseList>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<TVShowResponseList>, response: Response<TVShowResponseList>) {
                if(response.isSuccessful && response.body() != null){
                    val tvShowresponse : TVShowResponseList =  response.body()!!
                    adapter.updateList(tvShowresponse.results)
                }
            }
        }

        )

    }

    private fun onClickedTVShow(tvshow: TVShow) {
        findNavController().navigate(R.id.navigateToTVShowDetails, bundleOf("tvshowid" to tvshow.id))

    }
}