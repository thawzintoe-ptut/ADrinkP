package com.thawzintoe.ptut.adrinkp.viewholders

import android.view.View
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.randomColor
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_layout.view.*

class IngredientViewHolder(itemView: View,private val tapClick: onTapCategoryItem) :
        BaseViewHolder<DrinksIngredient>(itemView) {
    private lateinit var drinksIngredient: DrinksIngredient
    private val ingredientImage=itemView.viewImage
    private val ingredientText=itemView.viewText
    private val ingredientItem=itemView.viewItem

    override fun setData(data: DrinksIngredient) {
        drinksIngredient=data
        if(data.strIngredient1.isNullOrEmpty()){ itemView.visibility=View.GONE }
        ingredientImage.setImageResource(R.drawable.menu_ingredient)
        ingredientText.viewText.text=data.strIngredient1
        randomColor(ingredientItem)
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksIngredient.strIngredient1!!)
    }
}