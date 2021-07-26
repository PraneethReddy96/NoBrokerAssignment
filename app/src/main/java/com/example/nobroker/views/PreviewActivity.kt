package com.example.nobroker.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nobroker.R
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

   private lateinit var image: String
   private lateinit var title: String
   private lateinit var subTitle: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NoBroker)
        setContentView(R.layout.activity_preview)
        getIntents()
        setViews()

    }

    /* function to fetch the intent from search activity*/
    private fun getIntents() {
        image = intent.getStringExtra("image").toString()
        title = intent.getStringExtra("title").toString()
        subTitle = intent.getStringExtra("subTitle").toString()
    }

    /* function with parameters to set the data into the views*/
    private fun setViews() {
        Glide.with(ivPreViewImage).load(image).into(ivPreViewImage)
        tvPreviewTitle.setText(title)
        tvPreviewSubTitle.setText(subTitle)
    }
}