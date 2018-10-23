package com.thawzintoe.ptut.adrinkp.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.thawzintoe.ptut.adrinkp.models.base.BaseModel
import com.thawzintoe.ptut.adrinkp.utils.EmptyError
import com.thawzintoe.ptut.adrinkp.utils.Error
import com.thawzintoe.ptut.adrinkp.utils.scheduler
import com.thawzintoe.ptut.adrinkp.vos.lookUpList.GetLookUpResponse
import com.thawzintoe.ptut.adrinkp.vos.lookUpList.LookUpItem
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class LookUpModel private constructor(context: Context) : BaseModel() {
    companion object {
        private var INSTANCE: LookUpModel? = null
        fun getInstance(): LookUpModel {
            if (INSTANCE == null) {
                throw RuntimeException("ItemListModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initModel(context: Context) {
            INSTANCE = LookUpModel(context)
        }
    }
    fun getLookUpDetail(id: String, mLookUpLD: MutableLiveData<List<LookUpItem>>, errorLD: MutableLiveData<Error>) {
        mTheApi.getLookUp(id)
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (object : Observer<GetLookUpResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: GetLookUpResponse) {
                        if (t.drinks != null && t.drinks.isNotEmpty()) {
                            mLookUpLD.value = t.drinks
                        } else {
                            errorLD.value = EmptyError("Response Null Data")
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorLD.value = EmptyError("Response failed")
                    }

                })
    }
}