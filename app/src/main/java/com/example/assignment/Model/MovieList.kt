package com.example.assignment.Model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Movie List")val movieList: List<Movies>
)