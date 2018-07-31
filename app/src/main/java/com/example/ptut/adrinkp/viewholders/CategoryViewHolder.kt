package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.utils.UtilGeneral
import com.example.ptut.adrinkp.vos.categoryList.DrinksItem
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_layout.view.*
import java.util.*

class CategoryViewHolder(itemView:View,private val tapClick:onTapCategoryItem) : BaseViewHolder<DrinksItem>(itemView) {
    private lateinit var drinksItem:DrinksItem
    override fun setData(data: DrinksItem) {
        drinksItem=data
        UtilGeneral.randomColor(itemView.viewItem)
        if(data.strCategory.isNullOrEmpty()){
            itemView.visibility=View.GONE
        }
        itemView.viewText.text=data.strCategory
    }



    override fun onClick(v: View?) {
        tapClick.tapItem(drinksItem.strCategory!!)
    }
}