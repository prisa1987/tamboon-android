package com.omise.tamboon.presentation.charities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omise.android.tamboon.R
import com.omise.tamboon.data.entity.Charity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid_charity.view.*
import kotlin.properties.Delegates

class CharitiesAdapter : RecyclerView.Adapter<CharitiesViewHolder>() {

    var charities by Delegates.observable(listOf<Charity>()) { _, _, _ -> notifyDataSetChanged() }
    var itemOnClickListener: ((Pair<String, String>) -> Unit)? = null

    override fun getItemCount() = charities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_charity, parent, false)
        return CharitiesViewHolder(view, itemOnClickListener)
    }

    override fun onBindViewHolder(holder: CharitiesViewHolder, position: Int) {
        holder.charity = charities[position]
    }

}

class CharitiesViewHolder(val view: View, val onClickListener: ((Pair<String, String>) -> Unit)?) : RecyclerView.ViewHolder(view) {

    var charity by Delegates.observable(Charity()) { _, _, value -> setUpView(value) }

    fun setUpView(value: Charity) {
        view.tvCharity.text = value.name
        loadImage(value.logoURL)
        view.setOnClickListener { onClickListener?.invoke(value.id to value.name) }
    }

    fun loadImage(logoURL: String?) {
        Picasso.with(view.context)
                .load(logoURL)
                .into(view.ivCharity)
    }

}