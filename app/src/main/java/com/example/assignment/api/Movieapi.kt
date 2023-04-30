package com.example.assignment.api

import com.example.assignment.Model.MovieList
import com.example.assignment.Model.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Movieapi {
    @GET("{page}.json")
    suspend fun getmovies(@Path("page") page:String):Response<MovieList>
}