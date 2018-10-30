package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.widget.ImageView
import com.mmgoogleexpert.ptut.shared.model.BasePresenter
import com.thawzintoe.ptut.adrinkp.deligate.onTapCocktailDetail
import com.thawzintoe.ptut.adrinkp.models.SearchModel
import com.thawzintoe.ptut.adrinkp.mvp.views.SearchView
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem

class SearchPresenter : BasePresenter<SearchView>(),onTapCocktailDetail{


    private lateinit var mIngredientsLD: MutableLiveData<List<SearchDrinksItem>>
    override fun initPresenter(mView: SearchView) {
        super.initPresenter(mView)
        mIngredientsLD=MutableLiveData()
    }

    fun onNotifySearch(name:String){
        SearchModel.getInstance().getCocktailByName(name,mIngredientsLD,errorLD)
    }

    var ingredientLD:MutableLiveData<List<SearchDrinksItem>>? = null
        get() = mIngredientsLD

    override fun lunchCocktailDetail(searchDrinksItem: SearchDrinksItem,imageView: ImageView) {
        mView.lunchSearchDetailView(searchDrinksItem,imageView)
    }
}