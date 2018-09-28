package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import com.thawzintoe.ptut.adrinkp.viewholders.AlcoholViewHolder
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder

class AlcoholItemsAdapter(context: Context,private val tapClick: onTapCategoryItem): BaseRecyclerAdapter<AlcoholViewHolder, DrinksAlcohol>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksAlcohol> {
        val view: View =mLayoutInflator.inflate(R.layout.view_item_layout,parent,false)
        return AlcoholViewHolder(view,tapClick)
    }
}