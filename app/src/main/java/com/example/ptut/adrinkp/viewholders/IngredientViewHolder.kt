package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.deligate.onTapCategoryItem
import com.example.ptut.adrinkp.utils.UtilGeneral
import com.example.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_layout.view.*

class IngredientViewHolder(itemView: View,private val tapClick: onTapCategoryItem) : BaseViewHolder<DrinksIngredient>(itemView) {
    private lateinit var drinksIngredient: DrinksIngredient
    override fun setData(data: DrinksIngredient) {
        drinksIngredient=data
        if(data.strIngredient1.isNullOrEmpty()){
            itemView.visibility=View.GONE
        }
        itemView.viewImage.setImageResource(R.drawable.menu_ingredient)
        itemView.viewText.text=data.strIngredient1
        UtilGeneral.randomColor(itemView.viewItem)
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksIngredient.strIngredient1!!)
    }
}