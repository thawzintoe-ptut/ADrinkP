package com.example.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.example.ptut.adrinkp.deligate.onTapFilterItem
import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.example.ptut.adrinkp.models.FilterModel
import com.example.ptut.adrinkp.mvp.views.FilterView

class FilterPresenter :BasePresenter<FilterView>(),onTapFilterItem{


    private lateinit var mCategoryFilterLD:MutableLiveData<List<DrinksCategoryFilter>>

    override fun initPresenter(mView: FilterView) {
        super.initPresenter(mView)
        mCategoryFilterLD= MutableLiveData()
    }

    fun onFinishCategoryFilter(itemName:String){
        FilterModel.getInstance().getCategoryFilterList(itemName,mCategoryFilterLD,errorLD)
    }
    fun onFinishGlassFilter(itemName:String){
        FilterModel.getInstance().getGlassFilterList(itemName,mCategoryFilterLD,errorLD)
    }
    fun onFinishIngredientFilter(itemName:String){
        FilterModel.getInstance().getIngredientFilterList(itemName,mCategoryFilterLD,errorLD)
    }
    fun onFinishAlcoholFilter(itemName:String){
        FilterModel.getInstance().getAlcoholFilterList(itemName,mCategoryFilterLD,errorLD)
    }


    var categoryFilter:MutableLiveData<List<DrinksCategoryFilter>>?=null
        get() = mCategoryFilterLD

    override fun tapFilter(item: DrinksCategoryFilter) {
        mView.launchDetails(item)
    }

}