package com.example.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.vos.categoryList.DrinksItem
import com.example.ptut.adrinkp.viewholders.CategoryViewHolder
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder

class CategoryItemsAdapter(context:Context,private val tapClick:onTapCategoryItem):
        BaseRecyclerAdapter<CategoryViewHolder, DrinksItem>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksItem> {
        val view:View=mLayoutInflator.inflate(R.layout.view_item_layout,parent,false)
        return CategoryViewHolder(view,tapClick)
    }
}