package com.xheghun.dishapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xheghun.dishapp.databinding.ItemDishLayoutBinding
import com.xheghun.dishapp.models.entities.FavDish
import com.xheghun.dishapp.view.fragments.AllDishFragment

class FavDishAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<FavDishAdapter.ViewHolder>() {


    private var dishes: List<FavDish> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDishLayoutBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]

        Glide.with(fragment).load(dish.image).into(holder.ivDisImageView)
        holder.tvTitle.text = dish.title

        holder.itemView.setOnClickListener {
            if (fragment is AllDishFragment) {
                fragment.navToDishDetails(dish)
            }
        }
    }

    fun dishesList(list: List<FavDish>) {
        dishes = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dishes.size


    class ViewHolder(view: ItemDishLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val ivDisImageView = view.ivDishImage
        val tvTitle = view.tvDishTitle
    }
}