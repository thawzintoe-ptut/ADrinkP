package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.deligate.onTapCocktailDetail
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem
import kotlinx.android.synthetic.main.content_search_item.view.*

class SearchCocktailViewHolder(itemView:View,private val tapCocktailDetail:onTapCocktailDetail):
        BaseViewHolder<SearchDrinksItem>(itemView) {
    private lateinit var searchDrinksItem: SearchDrinksItem
    override fun setData(data: SearchDrinksItem) {
        searchDrinksItem=data
        if(data!=null){
            Glide.with(itemView).load(data.strDrinkThumb).into(itemView.strThumb)
            itemView.strName.text=data.strDrink
            itemView.strDesc.text=data.strInstructions
            itemView.strCategory.text=data.strCategory
            itemView.contentMore.setTextColor(itemView.resources.getColor(R.color.alizarin))
        }else{
            itemView.visibility=View.GONE
        }
    }

    override fun onClick(v: View?) {
        tapCocktailDetail.lunchCocktailDetail(searchDrinksItem)
    }
}