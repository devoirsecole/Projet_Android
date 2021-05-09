package com.example.tvproject.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvproject.presentation.Singletons
import com.example.tvproject.presentation.api.TVShowResponseList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowListViewModel : ViewModel() {

    val TVShowList : MutableLiveData<TVShowModel> = MutableLiveData()

    init{

        callApi()

    }

    private fun callApi() {
        TVShowList.value = TVShowLoader
        Singletons.tvShowAPI.getTopTVShow("dd2d91f98be44a04d0ba7a02272bb43d").enqueue(object: Callback<TVShowResponseList> {
            override fun onFailure(call: Call<TVShowResponseList>, t: Throwable) {
                TVShowList.value = TVShowError
            }

            override fun onResponse(call: Call<TVShowResponseList>, response: Response<TVShowResponseList>) {
                if(response.isSuccessful && response.body() != null){
                    val tvShowresponse : TVShowResponseList =  response.body()!!
                    TVShowList.value = TVShowSuccess(tvShowresponse.results)
                }else{
                    TVShowList.value = TVShowError
                }
            }
        }

        )
    }
}