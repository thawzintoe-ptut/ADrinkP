package com.example.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.example.ptut.adrinkp.deligate.onTapCocktailDetail
import com.example.ptut.adrinkp.viewholders.SearchCocktailViewHolder
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem

class SearchCocktailAdapter(context: Context, private val tapCocktailDetail: onTapCocktailDetail) :
        BaseRecyclerAdapter<SearchCocktailViewHolder, SearchDrinksItem>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SearchDrinksItem> {
        val view: View = mLayoutInflator.inflate(R.layout.content_search_item, parent, false)
        return SearchCocktailViewHolder(view, tapCocktailDetail)
    }


}