package com.xheghun.dishapp.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.xheghun.dishapp.R
import com.xheghun.dishapp.application.FavDishApplication
import com.xheghun.dishapp.databinding.FragmentDishDetailsBinding
import com.xheghun.dishapp.utils.capitalize
import com.xheghun.dishapp.viewmodel.FavDishViewModel
import com.xheghun.dishapp.viewmodel.FavDishViewModelFactory
import java.io.IOException
import java.util.*

class DishDetailsFragment : Fragment() {

    private val SCOPE = "DishDetailsFragment"

    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory(((requireActivity().application) as FavDishApplication).repository)
    }
    private lateinit var mBinding: FragmentDishDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentDishDetailsBinding.inflate(inflater,container,false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args:DishDetailsFragmentArgs by navArgs()

        args.let {
            try {
                Glide.with(requireActivity())
                    .load(it.dishDetails.image)
                    .centerCrop()
                    .listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e(SCOPE, "Error Loading image", e)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            resource.let {
                                Palette.from(resource!!.toBitmap()).generate() {
                                        palette ->
                                    val color = palette!!.vibrantSwatch?.rgb ?: 0
                                    mBinding.rlDishDetailMain.setBackgroundColor(color)
                                }
                            }
                            return false
                        }

                    })
                    .into(mBinding.ivDishImage)
            } catch (e: IOException) {
                e.printStackTrace()
            }


            val dish = it.dishDetails
            mBinding.tvTitle.text = dish.title
            mBinding.tvType.text = dish.type.capitalize()
            mBinding.tvCategory.text = dish.category
            mBinding.tvIngredients.text = dish.ingredients
            mBinding.tvCookingDirection.text = dish.directionsToCook
            mBinding.tvCookingTime.text = resources.getString(R.string.lbl_estimate_cooking_time, dish.cookingTime)
        }


        mBinding.ivFavoriteDish.setOnClickListener {
            args.dishDetails.favDish = !args.dishDetails.favDish
            mFavDishViewModel.updateDish(args.dishDetails)

            if(args.dishDetails.favDish) {
                mBinding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_selected))
            } else {
                mBinding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_unselected))
            }
        }


    }

}