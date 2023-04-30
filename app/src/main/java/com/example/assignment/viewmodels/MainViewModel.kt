package com.example.assignment.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.Model.MovieList
import com.example.assignment.Model.Movies
import com.example.assignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    fun getmoviee(page:String){
        viewModelScope.launch(Dispatchers.IO) {
         repository.getmovie(page)
        }
    }

    val movie:LiveData<MovieList>
        get() = repository.movies

  fun deletemovie(id:String){
      viewModelScope.launch(Dispatchers.IO) {
          repository.deletemovie(id)
      }
  }

    fun insertfav(id:String,value:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertfav(id,value)
        }
    }



}