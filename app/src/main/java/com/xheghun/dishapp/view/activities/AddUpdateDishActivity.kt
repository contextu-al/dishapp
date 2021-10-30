package com.xheghun.dishapp.view.activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.xheghun.dishapp.R
import com.xheghun.dishapp.databinding.ActivityAddUpdateDishBinding
import com.xheghun.dishapp.databinding.DialogCustomImageSelectionBinding

class AddUpdateDishActivity : AppCompatActivity(), View.OnClickListener {
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

        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)

        //Set the screen content from a layout resource.
        //The resource will be inflated, adding all top-level views to the screen.
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            dialog.dismiss()
            requestCamPermission()
        }

        binding.tvGallery.setOnClickListener {
            dialog.dismiss()
            requestStoragePermission()
        }
        // END

        //Start the dialog and display it on screen.
        dialog.show()
    }


    private fun requestStoragePermission() {

        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(this@AddUpdateDishActivity, "permission denied", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                showRationaleDialogForPermission()
            }


        }).onSameThread().check()
    }

    private fun requestCamPermission() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {


                report?.let {
                    if (report.areAllPermissionsGranted()) {
                        val mIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(mIntent, CAMERA)
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                showRationaleDialogForPermission()
            }


        }).onSameThread().check()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                data?.extras?.let {
                    val thumb: Bitmap = data.extras!!.get("data") as Bitmap
                    mBinding.ivDishImage.setImageBitmap(thumb)

                    mBinding.ivAddDishImage.setImageDrawable(
                        ContextCompat.getDrawable(this,R.drawable.ic_edit))

                }
            }
        }
    }

    private fun showRationaleDialogForPermission() {
        AlertDialog.Builder(this)
            .setMessage("Please allow necessary permissions to make app function properly")
            .setPositiveButton("OK") { _, _ ->

                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }.setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss() }.show()

    }

    companion object {
        private const val CAMERA = 1
    }

}