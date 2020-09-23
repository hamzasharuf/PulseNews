package com.hamzasharuf.pulse.ui.activities.details

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.utils.common.CommonFunctions.getBitmapFromUrl
import com.hamzasharuf.pulse.utils.common.Constants.ARTICLE_INTENT_TAG
import com.hamzasharuf.pulse.utils.extensions.timber
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var article: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        receiveData()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        timber("Hello")
    }

    private fun receiveData() {
        kotlin.runCatching {
            article = intent.getParcelableExtra(ARTICLE_INTENT_TAG)!!
            Glide.with(this)
                .load(article.thumbnail)
                .into(expandedImage)
            getBitmapFromUrl(expandedImage.context, article.thumbnail){
                if(it == null)
                    timber("null ya 3am")
                else
                    timber("Not Null")
                if (it!= null)
                Palette.from(it).generate {
                    if (it!=null){
                        collapsing_toolbar.setContentScrimColor(it.getMutedColor(R.attr.colorPrimary))
                }}
            }
            article_title.text = article.title
            date_and_time.text = article.date
            section_name.text = article.section
            authors_names.text = article.authors
            article_content.text = article.articleBody


        }.onFailure {
            // TODO : Do something
            timber(it.message.toString())
        }
    }

}