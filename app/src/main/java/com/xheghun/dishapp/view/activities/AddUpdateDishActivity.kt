package com.xheghun.dishapp.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xheghun.dishapp.R
import com.xheghun.dishapp.databinding.ActivityAddUpdateDishBinding
import com.xheghun.dishapp.databinding.DialogCustomImageSelectionBinding

class AddUpdateDishActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var mBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()

        mBinding.ivAddDishImage.setOnClickListener(this@AddUpdateDishActivity)
    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.iv_add_dish_image -> {

                Toast.makeText(this, "${v.id} clicked", Toast.LENGTH_SHORT).show()

                customImageSelectionDialog()

                return
            }
        }
    }

    /**
     * A function for ActionBar setup.
     */
    private fun setupActionBar() {
        setSupportActionBar(mBinding.toolbarAddDishActivity)

      /*  supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
*/
        mBinding.toolbarAddDishActivity.setNavigationOnClickListener { onBackPressed() }
    }



    private fun customImageSelectionDialog() {
        val dialog = Dialog(this@AddUpdateDishActivity)

        val binding: DialogCustomImageSelectionBinding = DialogCustomImageSelectionBinding.inflate(layoutInflater)

        //Set the screen content from a layout resource.
        //The resource will be inflated, adding all top-level views to the screen.
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            Toast.makeText(this@AddUpdateDishActivity, "You have clicked on the Camera.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Toast.makeText(this@AddUpdateDishActivity, "You have clicked on the Gallery.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        // END

        //Start the dialog and display it on screen.
        dialog.show()
    }


}