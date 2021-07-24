package com.example.nobroker.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nobroker.R
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {

lateinit var image : String
lateinit var title : String
lateinit var subTitle : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        getIntents()
        setViews()

    }

    private fun getIntents() {
         image = intent.getStringExtra("image").toString()
         title = intent.getStringExtra("title").toString()
         subTitle = intent.getStringExtra("subTitle").toString()
    }

    private fun setViews() {
         Glide.with(ivPreViewImage).load(image).into(ivPreViewImage)
         tvPreviewTitle.setText(title)
         tvPreviewSubTitle.setText(subTitle)
    }
}