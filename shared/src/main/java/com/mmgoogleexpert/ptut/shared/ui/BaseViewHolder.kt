package com.mmgoogleexpert.ptut.shared.ui

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mData: W? = null

    init {

        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: W)

}