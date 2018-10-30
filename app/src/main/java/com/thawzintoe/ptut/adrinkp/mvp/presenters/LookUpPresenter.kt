package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.mmgoogleexpert.ptut.shared.model.BasePresenter
import com.thawzintoe.ptut.adrinkp.models.LookUpModel
import com.thawzintoe.ptut.adrinkp.models.RandomModel
import com.thawzintoe.ptut.adrinkp.mvp.views.LookUpView
import com.thawzintoe.ptut.adrinkp.vos.lookUpList.LookUpItem

class LookUpPresenter: BasePresenter<LookUpView>() {
    private lateinit var mLookUpLD: MutableLiveData<List<LookUpItem>>
    override fun initPresenter(mView: LookUpView) {
        super.initPresenter(mView)
        mLookUpLD= MutableLiveData()
    }
    fun onNotifyLookUpDetail(id:String){
        LookUpModel.getInstance().getLookUpDetail(id,mLookUpLD,errorLD)
    }
    var lookUpLD:MutableLiveData<List<LookUpItem>>?=null
        get() = mLookUpLD
}