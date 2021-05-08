package com.example.tvproject.presentation.api

import com.example.tvproject.presentation.list.TVShow

data class TVShowResponseList(

    val page : Int,
    val total_results : Int,
    val total_pages : Int,

    val results : List<TVShow>

)