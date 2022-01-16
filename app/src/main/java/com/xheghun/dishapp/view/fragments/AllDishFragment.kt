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
import com.xheghun.dishapp.R
import com.xheghun.dishapp.application.FavDishApplication
import com.xheghun.dishapp.databinding.FragmentAllDishBinding
import com.xheghun.dishapp.view.activities.AddUpdateDishActivity
import com.xheghun.dishapp.viewmodel.FavDishViewModel
import com.xheghun.dishapp.viewmodel.FavDishViewModelFactory
import com.xheghun.dishapp.viewmodel.HomeViewModel

class AllDishFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentAllDishBinding? = null

    private val mFavDiViewModel: FavDishViewModel by viewModels {
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
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentAllDishBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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

        mFavDiViewModel.allDishesList.observe(viewLifecycleOwner) {
            dishes ->
            dishes.let {
                for (item in it) {
                    Log.i("Dish title", "id: ${item.id} , title: ${item.title}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}