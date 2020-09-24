package com.hamzasharuf.pulse.ui.activities.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.databinding.ActivityDetailsBinding
import com.hamzasharuf.pulse.utils.common.Constants.ARTICLE_INTENT_TAG
import com.hamzasharuf.pulse.utils.extensions.timber
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var article: News
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        receiveData()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun receiveData() {
        kotlin.runCatching {
            article = intent.getParcelableExtra(ARTICLE_INTENT_TAG)!!
            binding.item = article
        }.onFailure {
            // TODO : Do something
            timber(it.message.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}