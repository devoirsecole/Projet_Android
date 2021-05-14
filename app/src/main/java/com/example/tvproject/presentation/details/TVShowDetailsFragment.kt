package com.example.tvproject.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tvproject.R
import com.example.tvproject.presentation.Singletons
import com.example.tvproject.presentation.api.TVShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TVShowDetailsFragment : Fragment() {

    private lateinit var textviewname : TextView
    private lateinit var textviewnumberepisodes : TextView
    private lateinit var textviewnumberseasons : TextView
    private lateinit var textviewfirstAir : TextView
    private lateinit var textviewtype : TextView


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        textviewname = view.findViewById(R.id.tvshow_details_name)
        textviewnumberepisodes = view.findViewById(R.id.tvshow_details_nEpisodes)
        textviewnumberseasons = view.findViewById(R.id.tvshow_details_nSeasons)
        textviewfirstAir = view.findViewById(R.id.tvshow_details_firstair)
        textviewtype= view.findViewById(R.id.tvshow_details_Type)


        callApi()
    }

    private fun callApi(){

        val id = arguments?.getInt("tvshowid") ?: -1

    Singletons.tvShowAPI.getTVShowDetails(id).enqueue(object :Callback<TVShowDetailsResponse>{
        override fun onFailure(call: Call<TVShowDetailsResponse>, t: Throwable) {

        }

        override fun onResponse(call: Call<TVShowDetailsResponse>, response: Response<TVShowDetailsResponse>) {
            if(response.isSuccessful && response.body() != null){
                textviewname.text = response.body()!!.name
                textviewfirstAir.text = response.body()!!.first_air_date
                textviewnumberepisodes.text = response.body()!!.number_of_episodes.toString()
                textviewnumberseasons.text = response.body()!!.number_of_seasons.toString()
                textviewtype.text = response.body()!!.type
            }
        }

    })

    }
}