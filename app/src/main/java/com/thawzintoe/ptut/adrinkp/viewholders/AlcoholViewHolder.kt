package com.thawzintoe.ptut.adrinkp.viewholders

import android.view.View
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.randomColor
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import kotlinx.android.synthetic.main.view_item_layout.view.*

class AlcoholViewHolder(itemView: View, private val tapClick: onTapCategoryItem) : BaseViewHolder<DrinksAlcohol>(itemView) {
    private lateinit var drinksAlcohol: DrinksAlcohol
    private val alcoholViewImage = itemView.viewImage
    private val alcoholViewText = itemView.viewText
    private val alcoholViewItem = itemView.viewItem

    override fun setData(data: DrinksAlcohol) {
        drinksAlcohol = data
        if (data.strAlcoholic.isNullOrEmpty()) {
            itemView.visibility = View.GONE
        }
        alcoholViewImage.setImageResource(R.drawable.menu_alcohol)
        alcoholViewText.text = data.strAlcoholic
        randomColor(alcoholViewItem)
    }

    override fun onClick(v: View?) {
        tapClick.tapItem(drinksAlcohol.strAlcoholic!!)
    }
}