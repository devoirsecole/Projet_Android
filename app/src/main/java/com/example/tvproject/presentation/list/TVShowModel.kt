package com.example.tvproject.presentation.list

sealed class TVShowModel

data class TVShowSuccess(val TVShowList: List<TVShow>): TVShowModel()
object TVShowLoader : TVShowModel()
object TVShowError : TVShowModel()