package com.muthohhari.visityogyakarta.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.muthohhari.visityogyakarta.R
import com.muthohhari.visityogyakarta.models.Data
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {
    companion object {
        var EXTRADATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Data>(EXTRADATA)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(data.gambarPariwisata).into(imagePlace)
        namePlace.text = data.namaPariwisata
        location.text = data.alamatPariwisata
        description.text = data.detailPariwisata
        getDirection.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val address = data.alamatPariwisata
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.co.in/maps?q=" + address))
                startActivity(intent)
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
