package com.thawzintoe.ptut.adrinkp.viewholders

import android.view.View
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.randomColor
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem
import kotlinx.android.synthetic.main.view_item_layout.view.*

class CategoryViewHolder(itemView:View,private val tapClick:onTapCategoryItem) : BaseViewHolder<DrinksItem>(itemView) {
    private lateinit var drinksItem:DrinksItem
    private val categoryViewItem=itemView.viewItem
    private val categoryViewText=itemView.viewText

    override fun setData(data: DrinksItem) {
        drinksItem=data
        randomColor(categoryViewItem)
        if(data.strCategory.isNullOrEmpty()){
            itemView.visibility=View.GONE
        }
        categoryViewText.text=data.strCategory
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksItem.strCategory!!)
    }
}