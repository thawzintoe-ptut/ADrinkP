package com.thawzintoe.ptut.adrinkp.viewholders

import android.view.View
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.randomColor
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass
import kotlinx.android.synthetic.main.view_item_layout.view.*

class GlassViewHolder(itemView:View,private val tapClick: onTapCategoryItem) : BaseViewHolder<DrinksGlass>(itemView) {
    private lateinit var drinksGlass: DrinksGlass
    private val glassImage=itemView.viewImage
    private val glassText=itemView.viewText
    private val glassItem=itemView.viewItem
    override fun setData(data: DrinksGlass) {
        drinksGlass=data
        if(data.strGlass.isNullOrEmpty()){ itemView.visibility=View.GONE }
        glassImage.setImageResource(R.drawable.menu_glass)
        glassText.text=data.strGlass
        randomColor(glassItem)
    }
    override fun onClick(v: View?) {
        tapClick.tapItem(drinksGlass.strGlass!!)
    }
}