package com.thawzintoe.ptut.adrinkp.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.thawzintoe.ptut.adrinkp.utils.scheduler
import com.thawzintoe.ptut.adrinkp.vos.randomList.RandomDrinkResponse
import com.thawzintoe.ptut.adrinkp.vos.randomList.RandomDrinksItem
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RandomModel private constructor(context: Context) : BaseModel() {
    companion object {
        private var INSTANCE: RandomModel? = null
        fun getInstance(): RandomModel {
            if (INSTANCE == null) {
                throw RuntimeException("ItemListModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initModel(context: Context) {
            INSTANCE = RandomModel(context)
        }
    }

    fun getRandomDrink(drinkLD: MutableLiveData<List<RandomDrinksItem>>, errorLD: MutableLiveData<Error>) {
        mTheApi.getRandomDrink()
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<RandomDrinkResponse> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: RandomDrinkResponse) {
                        if (t.drinks != null && t.drinks.isNotEmpty()) {
                            drinkLD.value = t.drinks as List<RandomDrinksItem>?
                        } else {
                            errorLD.value = EmptyError("No  Data")
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorLD.value = NetworkError("No Response Data")
                    }
                }
                )
    }


}