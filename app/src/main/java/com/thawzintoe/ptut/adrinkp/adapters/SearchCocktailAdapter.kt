package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.thawzintoe.ptut.adrinkp.deligate.onTapCocktailDetail
import com.thawzintoe.ptut.adrinkp.utils.inflate
import com.thawzintoe.ptut.adrinkp.viewholders.SearchCocktailViewHolder
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem

class SearchCocktailAdapter(context: Context, private val tapCocktailDetail: onTapCocktailDetail) :
        BaseRecyclerAdapter<SearchCocktailViewHolder, SearchDrinksItem>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SearchDrinksItem> {
        return SearchCocktailViewHolder(parent.inflate(R.layout.content_search_item), tapCocktailDetail)
    }
}