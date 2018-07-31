package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.utils.UtilGeneral
import com.example.ptut.adrinkp.vos.categoryList.DrinksGlass
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_layout.view.*

class GlassViewHolder(itemView:View,private val tapClick: onTapCategoryItem) : BaseViewHolder<DrinksGlass>(itemView) {
    private lateinit var drinksGlass: DrinksGlass
    override fun setData(data: DrinksGlass) {
        drinksGlass=data
        if(data.strGlass.isNullOrEmpty()){
            itemView.visibility=View.GONE
        }
        itemView.viewImage.setImageResource(R.drawable.menu_glass)
        itemView.viewText.text=data.strGlass
        UtilGeneral.randomColor(itemView.viewItem)
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksGlass.strGlass!!)
    }
}