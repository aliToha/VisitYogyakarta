package com.muthohhari.visityogyakarta.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muthohhari.visityogyakarta.R
import com.muthohhari.visityogyakarta.models.Data
import com.muthohhari.visityogyakarta.rvHomeUI
import org.jetbrains.anko.AnkoContext

class AdapterHome(private val items: List<Data>, private val clickListener: (Data) -> Unit) :
    RecyclerView.Adapter<AdapterHome.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val namePlace = view.findViewById<TextView>(R.id.name_place)
            private val locationPlace = view.findViewById<TextView>(R.id.location)
            private val imagePlace = view.findViewById<ImageView>(R.id.image)

            fun bindItem(items: Data, clickListener: (Data) -> Unit) {
                namePlace.text = items.namaPariwisata
                locationPlace.text = items.alamatPariwisata
                Glide.with(itemView.context).load(items.gambarPariwisata).into(imagePlace)
                itemView.setOnClickListener { clickListener(items) }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                rvHomeUI().createView(
                    AnkoContext.create(parent.context, parent)
                )
            )
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
            holder.bindItem(items[postion], clickListener)
        }

    }