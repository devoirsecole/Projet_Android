package com.example.tvproject.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tvproject.R
import com.example.tvproject.presentation.Singletons
import com.example.tvproject.presentation.api.TVShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowDetailsFragment : Fragment() {

    private lateinit var textviewname : TextView

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
            }
        }

    })

    }
}