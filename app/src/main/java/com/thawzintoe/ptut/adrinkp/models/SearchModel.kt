package com.thawzintoe.ptut.adrinkp.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.thawzintoe.ptut.adrinkp.network.response.GetCocktailSearchResponse
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.thawzintoe.ptut.adrinkp.utils.scheduler
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchModel(context: Context): BaseModel() {
    companion object {
        var INSTANCE:SearchModel?=null
        fun getInstance():SearchModel{
            if(INSTANCE==null){
                throw RuntimeException("FilterModel is being invoked before initializing")
            }
            val i= INSTANCE
            return i!!
        }
        fun inintModel(context: Context){
            INSTANCE= SearchModel(context)
        }
    }

    fun getCocktailByName(name:String,mCocktailItem:MutableLiveData<List<SearchDrinksItem>>,
                          errorLD:MutableLiveData<Error>){

        mTheApi.getCocktailByName(name)
                .subscribeOn(scheduler)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:Observer<GetCocktailSearchResponse>{
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(getCocktailSearchResponse: GetCocktailSearchResponse) {
                     if(getCocktailSearchResponse.drinks != null){
                            mCocktailItem.value=getCocktailSearchResponse.drinks
                     }else{
                         errorLD.value= EmptyError("No Data")
                     }
                    }
                    override fun onError(e: Throwable) {
                        errorLD.value= NetworkError(e.message!!)
                    }
                } )
    }

}