package com.example.assignment

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.assignment.Fragments.First_Fragment
import com.example.assignment.Fragments.Second_Fragment
import com.example.assignment.Fragments.Third_Fragment
import com.example.assignment.Model.MovieList
import com.example.assignment.api.Movieapi
import com.example.assignment.api.retrofithelper
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.repository.Repository
import com.example.assignment.viewmodels.MainViewModel
import com.example.assignment.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

 class  MainActivity : AppCompatActivity() {
     val fragment1: Fragment = First_Fragment()
     val fragment2: Fragment = Second_Fragment()
     val fragment3: Fragment = Third_Fragment()
     val fm: FragmentManager = supportFragmentManager
     var active = fragment1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



setupBottomNavigation()




    }

     private fun setupBottomNavigation() {
         fm.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit();
         fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
         fm.beginTransaction().add(R.id.nav_host_fragment,fragment1, "1").commit();
         val navigation = findViewById<View>(R.id.nvg) as BottomNavigationView

         navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
     }

     private val mOnNavigationItemSelectedListener=NavigationBarView.OnItemSelectedListener {
         when(it.itemId){
             R.id.display-> {
                 supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 fm.beginTransaction().hide(active).show(fragment1).commit()
                 active = fragment1
             }
             R.id.fav-> {
                 supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 fm.beginTransaction().hide(active).show(fragment2).commit()
                 active = fragment2
             }
             R.id.profile-> {
                 supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 fm.beginTransaction().hide(active).show(fragment3).commit()
                 active = fragment3
             }
         }
         false
     }





}