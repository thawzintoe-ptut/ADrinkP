package com.example.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.example.ptut.adrinkp.viewholders.IngredientViewHolder
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder

class IngredientItemsAdapter(context: Context,private val tapClick: onTapCategoryItem):
        BaseRecyclerAdapter<IngredientViewHolder, DrinksIngredient>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksIngredient> {
        val view: View =mLayoutInflator.inflate(R.layout.view_item_layout,parent,false)
        return IngredientViewHolder(view,tapClick)
    }
}