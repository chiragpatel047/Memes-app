package com.techydeveloper.memes

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.telephony.mbms.DownloadRequest
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.techydeveloper.memes.databinding.ActivityMainBinding
import com.techydeveloper.memes.factory.MainViewModelFactory
import com.techydeveloper.memes.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    var imageUrl : String = ""
    var captions : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        supportActionBar?.hide()

        mainViewModel = ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getRandomMeme()

        this.cacheDir.deleteRecursively()

        mainViewModel.memeLiveData.observe(this) {
            imageUrl = it.url!!
            captions = it.title!!

            Glide.with(this).load(imageUrl).into(binding.mainImage)
            binding.captions.text = captions

        }

        binding.viewShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, imageUrl)
            startActivity(Intent.createChooser(intent, "Share via"))
        }

        binding.viewDownload.setOnClickListener {
            val request = DownloadManager.Request(Uri.parse(imageUrl))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE
                        or  DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("Meme")
                .setDescription("Random meme")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                        or DownloadManager.Request.NETWORK_WIFI)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,System.currentTimeMillis().toString()+".jpg")

            val downloadManager : DownloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }

        binding.viewNext.setOnClickListener {
            binding.captions.text = ". . . . ."
            mainViewModel.getRandomMeme()
        }

    }
}