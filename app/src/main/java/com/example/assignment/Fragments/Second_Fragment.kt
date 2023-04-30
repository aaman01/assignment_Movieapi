package com.example.assignment.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.Database.MovieDatabase
import com.example.assignment.R
import com.example.assignment.Reycler_Adapter.Adapter
import com.example.assignment.Reycler_Adapter.Adapter2
import com.example.assignment.api.Movieapi
import com.example.assignment.api.retrofithelper
import com.example.assignment.databinding.FragmentFirstBinding
import com.example.assignment.databinding.FragmentSecondBinding
import com.example.assignment.repository.Repository
import com.example.assignment.viewmodels.MainViewModel
import com.example.assignment.viewmodels.MainViewModelFactory
import kotlinx.coroutines.launch


class Second_Fragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()

        lifecycleScope.launch {

            MovieDatabase.getDatabase(requireActivity()).moviedao().getfav().observe(
                viewLifecycleOwner, Observer {
                    binding.recyclerview.apply {
                        layoutManager = LinearLayoutManager(requireView().context)
                        adapter = Adapter2(mainViewModel).apply {
                            setdata(it)
                        }
                    }
                }
            )


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private fun initialization() {

        val movieapi = retrofithelper.getInstance().create(Movieapi::class.java)
        val movieDatabase = MovieDatabase.getDatabase(requireContext())
        val repository = Repository(movieapi, movieDatabase)
        mainViewModel = ViewModelProvider(
            this@Second_Fragment,
            MainViewModelFactory(repository)
        )[MainViewModel::class.java]
        mainViewModel.getmoviee("1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}