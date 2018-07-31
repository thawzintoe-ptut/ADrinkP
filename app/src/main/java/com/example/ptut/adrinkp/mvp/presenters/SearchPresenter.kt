package com.example.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.example.ptut.adrinkp.deligate.onTapCocktailDetail
import com.example.ptut.adrinkp.models.SearchModel
import com.example.ptut.adrinkp.mvp.views.SearchView
import com.example.ptut.adrinkp.vos.searchList.IngredientsItem
import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem

class SearchPresenter :BasePresenter<SearchView> (),onTapCocktailDetail{


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

    override fun lunchCocktailDetail(searchDrinksItem: SearchDrinksItem) {
        mView.lunchSearchDetailView(searchDrinksItem)
    }
}