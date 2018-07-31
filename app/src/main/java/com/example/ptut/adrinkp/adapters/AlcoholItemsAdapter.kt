package com.example.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import com.example.ptut.adrinkp.viewholders.AlcoholViewHolder
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder

class AlcoholItemsAdapter(context: Context,private val tapClick: onTapCategoryItem): BaseRecyclerAdapter<AlcoholViewHolder, DrinksAlcohol>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksAlcohol> {
        val view: View =mLayoutInflator.inflate(R.layout.view_item_layout,parent,false)
        return AlcoholViewHolder(view,tapClick)
    }
}