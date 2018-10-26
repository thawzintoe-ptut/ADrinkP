package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem
import com.thawzintoe.ptut.adrinkp.models.ItemListModel
import com.thawzintoe.ptut.adrinkp.mvp.views.CategoryView

class CategoryPresenter: BasePresenter<CategoryView>(),onTapCategoryItem {
    private var mDrinkItemLD:MutableLiveData<List<DrinksItem>>?=null
    private var mDrinkGlassLD:MutableLiveData<List<DrinksGlass>>?=null
    private var mDrinkIngredientLD:MutableLiveData<List<DrinksIngredient>>?=null
    private var mDrinkAlcoholLD:MutableLiveData<List<DrinksAlcohol>>?=null

    override fun initPresenter(mView: CategoryView) {
        super.initPresenter(mView)
        mDrinkItemLD=MutableLiveData()
        mDrinkGlassLD= MutableLiveData()
        mDrinkIngredientLD= MutableLiveData()
        mDrinkAlcoholLD= MutableLiveData()
    }

    fun onFinishCategory(itemName:String){
        ItemListModel.getInstance().getDrinkCategoryList(itemName,mDrinkItemLD!!,errorLD)
    }

    fun onFinishGlass(itemName:String){
        ItemListModel.getInstance().getDrinkGlassList(itemName,mDrinkGlassLD!!,errorLD)
    }
    fun onFinishIngredient(itemName:String){
        ItemListModel.getInstance().getIngredientList(itemName,mDrinkIngredientLD!!,errorLD)
    }
    fun onFinishAlcohol(itemName:String){
        ItemListModel.getInstance().getAlcoholList(itemName,mDrinkAlcoholLD!!,errorLD)
    }

    var drinkLD:MutableLiveData<List<DrinksItem>>? = null
        get() = mDrinkItemLD!!

    var drinkGlassLD:MutableLiveData<List<DrinksGlass>>? = null
        get() = mDrinkGlassLD!!

    var drinkIngredientLD:MutableLiveData<List<DrinksIngredient>>? = null
        get() = mDrinkIngredientLD!!
    var drinkAlcoholLD:MutableLiveData<List<DrinksAlcohol>>? = null
        get() = mDrinkAlcoholLD!!

    override fun tapItem(itemName: String) {
        mView.launchFilter(itemName)
    }
}