package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.utils.UtilGeneral
import com.example.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_layout.view.*

class AlcoholViewHolder(itemView: View,private val tapClick:onTapCategoryItem) : BaseViewHolder<DrinksAlcohol>(itemView) {
    private lateinit var drinksAlcohol: DrinksAlcohol
    override fun setData(data: DrinksAlcohol) {
        drinksAlcohol=data
        if(data.strAlcoholic.isNullOrEmpty()){
            itemView.visibility=View.GONE
        }
        itemView.viewImage.setImageResource(R.drawable.menu_alcohol)
        itemView.viewText.text=data.strAlcoholic
        UtilGeneral.randomColor(itemView.viewItem)
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksAlcohol.strAlcoholic!!)
    }
}