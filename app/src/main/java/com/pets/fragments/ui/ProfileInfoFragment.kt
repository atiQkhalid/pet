package com.pets

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.app.onlive.utils.FileUriUtils
import com.permissionx.guolindev.PermissionX
import com.pets.base.BaseFragment
import com.pets.core.utils.Const.IS_IMAGE_VIDEO
import com.pets.databinding.FragmentProfileInfoBinding
import java.io.File
import java.util.*


class ProfileInfoFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            this.tvDate.setOnClickListener(this@ProfileInfoFragment)
            this.addImage.setOnClickListener(this@ProfileInfoFragment)
            this.ivProfilePic.setOnClickListener(this@ProfileInfoFragment)
            this.male.setOnClickListener(this@ProfileInfoFragment)
            this.female.setOnClickListener(this@ProfileInfoFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            var date = "$dayOfMonth - ${monthOfYear + 1} - $year"
            binding.tvDate.text = date

        }, year, month, day)
        dpd.show()
    }

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    fun male(){
        binding.male.background = resources.getDrawable(R.drawable.bg_rectangle_filled_primary)
        binding.female.background = resources.getDrawable(R.drawable.bg_rectangle_with_borders)
        binding.male.setTextColor(R.color.colorWhite)
        binding.female.setTextColor(R.color.primary)
        binding.male.setPadding(15, 15, 15, 15)

    }

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    fun female(){
        binding.female.background = resources.getDrawable(R.drawable.bg_rectangle_filled_primary)
        binding.male.background = resources.getDrawable(R.drawable.bg_rectangle_with_borders)
        binding.female.setTextColor(R.color.colorWhite)
        binding.male.setTextColor(R.color.colorPrimary)
        binding.female.setPadding(15, 15, 15, 15)
    }


    @SuppressLint("InlinedApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun openGallery(){
        PermissionX.init(mainActivity)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .request { allGranted, grantedList, deniedList ->
                pickFromGallery(IS_IMAGE_VIDEO)
            }
    }

    var selection: String = ""
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun pickFromGallery(selectionType: String = IS_IMAGE_VIDEO) {
        selection = selectionType
        Intent(Intent.ACTION_GET_CONTENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            when (selectionType) {
                IS_IMAGE_VIDEO -> putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*"))
            }
            selectionFromGalleryResult.launch(this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private val selectionFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val filePath =
                    result?.data?.data?.let { FileUriUtils.getRealPath(mainActivity, it) } ?: ""
                val file = File(filePath)
                binding.ivProfilePic.setImageURI(Uri.fromFile(file))
            }
        }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_profilePic -> openGallery()
            R.id.addImage -> openGallery()
            R.id.tv_date -> clickDataPicker()
            R.id.male -> male()
            R.id.female -> female()
        }
    }
}