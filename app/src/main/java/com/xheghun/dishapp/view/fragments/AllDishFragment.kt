package com.xheghun.dishapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.xheghun.dishapp.R
import com.xheghun.dishapp.application.FavDishApplication
import com.xheghun.dishapp.databinding.FragmentAllDishBinding
import com.xheghun.dishapp.view.activities.AddUpdateDishActivity
import com.xheghun.dishapp.view.adapters.FavDishAdapter
import com.xheghun.dishapp.viewmodel.FavDishViewModel
import com.xheghun.dishapp.viewmodel.FavDishViewModelFactory
import com.xheghun.dishapp.viewmodel.HomeViewModel

class AllDishFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var  mBinding: FragmentAllDishBinding




    private val mFavDiViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAllDishBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add_dish -> startActivity(Intent(requireActivity(),AddUpdateDishActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvAllDishesList.layoutManager = GridLayoutManager(requireActivity(), 2)
        val favDishAdapter = FavDishAdapter(this)

        mBinding.rvAllDishesList.adapter = favDishAdapter

        mFavDiViewModel.allDishesList.observe(viewLifecycleOwner) {
            dishes ->
            dishes.let {
                if(it.isNotEmpty()) {
                    mBinding.rvAllDishesList.visibility = View.VISIBLE
                    mBinding.textHome.visibility = View.GONE
                    favDishAdapter.dishesList(it)
                } else {
                    mBinding.rvAllDishesList.visibility = View.GONE
                    mBinding.rvAllDishesList.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}