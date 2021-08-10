package com.example.nobroker.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.nobroker.R


class PreviewActivity : AppCompatActivity() {


    lateinit var ivPreViewImage: ImageView
    lateinit var tvPreviewTitle: TextView
    lateinit var tvPreviewSubTitle: TextView
    private lateinit var image: String
    private lateinit var title: String
    private lateinit var subTitle: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NoBroker)
        setContentView(R.layout.activity_preview)
        initViews()
        getIntents()
        setViews()

    }

    private fun initViews() {
        ivPreViewImage = findViewById<ImageView>(R.id.ivPreViewImage)
        tvPreviewTitle = findViewById<TextView>(R.id.tvPreviewTitle)
        tvPreviewSubTitle = findViewById<TextView>(R.id.tvPreviewSubTitle)

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