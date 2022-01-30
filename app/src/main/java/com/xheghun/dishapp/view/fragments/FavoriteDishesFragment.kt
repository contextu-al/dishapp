package com.xheghun.dishapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xheghun.dishapp.application.FavDishApplication
import com.xheghun.dishapp.databinding.FragmentAllDishBinding
import com.xheghun.dishapp.databinding.FragmentFavDishesBinding
import com.xheghun.dishapp.models.database.FavDishRepository
import com.xheghun.dishapp.viewmodel.DashboardViewModel
import com.xheghun.dishapp.viewmodel.FavDishViewModel
import com.xheghun.dishapp.viewmodel.FavDishViewModelFactory

class FavoriteDishesFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentFavDishesBinding? = null
    private val mFavDishViewModel: FavDishViewModel  by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentFavDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFavDishViewModel.favDishesList.observe(viewLifecycleOwner) {
            it.let {
                if(it.isNotEmpty()) {
                    it.forEach { fav -> Log.v("FavDish", "fav is $fav") }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}