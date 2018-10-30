package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.mmgoogleexpert.ptut.shared.model.BasePresenter
import com.thawzintoe.ptut.adrinkp.models.RandomModel
import com.thawzintoe.ptut.adrinkp.mvp.views.RandomView
import com.thawzintoe.ptut.adrinkp.vos.lookUpList.LookUpItem
import com.thawzintoe.ptut.adrinkp.vos.randomList.RandomDrinksItem

class RandomPresenter: BasePresenter<RandomView>() {
    private lateinit var mDrinkLD:MutableLiveData<List<RandomDrinksItem>>

    override fun initPresenter(mView: RandomView) {
        super.initPresenter(mView)
        mDrinkLD= MutableLiveData()

    }
    fun onNotifyRandom(){
        RandomModel.getInstance().getRandomDrink(mDrinkLD,errorLD)
    }



    var randomDrinkLD:MutableLiveData<List<RandomDrinksItem>>?=null
        get() = mDrinkLD


}