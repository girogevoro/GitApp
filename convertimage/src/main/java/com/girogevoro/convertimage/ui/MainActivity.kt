package com.girogevoro.convertimage.ui

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.girogevoro.convertimage.data.ImageConverterPNGImpl
import com.girogevoro.convertimage.data.ImageLoaderImpl
import com.girogevoro.convertimage.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        MainPresenter(
            ImageLoaderImpl(),
            ImageConverterPNGImpl()
        )
    }

    private val singlePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                presenter.onConvert()
            }
            else -> {
                showMessage(Messages.Error)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.convertButton.setOnClickListener {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
               showMessage(Messages.Error)
            } else {
                singlePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    override fun showLoading(progress: Int) {
        binding.linearProgress.progress = progress
    }

    override fun showMessage(message: Messages) {
        Toast.makeText(this, message.id, Toast.LENGTH_SHORT).show()
    }

    override fun showImage(imageBitmap: Bitmap) {
        binding.imageView.setImageBitmap(imageBitmap)
    }



}