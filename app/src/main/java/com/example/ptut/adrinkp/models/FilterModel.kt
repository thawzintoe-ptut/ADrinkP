package com.example.ptut.adrinkp.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.example.ptut.adrinkp.models.base.BaseModel
import com.example.ptut.adrinkp.network.response.GetCategoryFilterResponse
import com.example.ptut.adrinkp.utils.EmptyError
import com.example.ptut.adrinkp.utils.Error
import com.example.ptut.adrinkp.utils.NetworkError
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FilterModel private constructor(context: Context):BaseModel() {
    companion object {
        var INSTANCE:FilterModel?=null
        fun getInstance():FilterModel{
            if(INSTANCE==null){
                throw RuntimeException("FilterModel is being invoked before initializing")
            }
            val i= INSTANCE
            return i!!
        }
        fun inintModel(context: Context){
            INSTANCE= FilterModel(context)
        }
    }

    fun getCategoryFilterList(itemName:String,mFilterLD:MutableLiveData<List<DrinksCategoryFilter>>
                            ,mErrorLD:MutableLiveData<Error>){
        mTheApi.getFilterItemList(itemName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetCategoryFilterResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(getFilterResponse: GetCategoryFilterResponse) {
                       if(getFilterResponse.drinks!!.isNotEmpty() && getFilterResponse!=null){
                           mFilterLD.value=getFilterResponse.drinks
                       }else{
                           mErrorLD.value=EmptyError("No Data")
                       }
                    }

                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }

                });

    }

    fun getGlassFilterList(itemName: String, mFilterLD: MutableLiveData<List<DrinksCategoryFilter>>,
                           mErrorLD: MutableLiveData<Error>){
        mTheApi.getFilterGlassList(itemName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetCategoryFilterResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(getFilterResponse: GetCategoryFilterResponse) {
                        if(getFilterResponse.drinks!!.isNotEmpty() && getFilterResponse!=null){
                            mFilterLD.value=getFilterResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }

                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }

                });
    }
    fun getIngredientFilterList(itemName: String, mFilterLD: MutableLiveData<List<DrinksCategoryFilter>>,
                           mErrorLD: MutableLiveData<Error>){
        mTheApi.getFilterIngredientList(itemName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetCategoryFilterResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(getFilterResponse: GetCategoryFilterResponse) {
                        if(getFilterResponse.drinks!!.isNotEmpty() && getFilterResponse!=null){
                            mFilterLD.value=getFilterResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }

                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }

                });
    }
    fun getAlcoholFilterList(itemName: String, mFilterLD: MutableLiveData<List<DrinksCategoryFilter>>,
                           mErrorLD: MutableLiveData<Error>){
        mTheApi.getFilterAlcoholList(itemName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetCategoryFilterResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(getFilterResponse: GetCategoryFilterResponse) {
                        if(getFilterResponse.drinks!!.isNotEmpty() && getFilterResponse!=null){
                            mFilterLD.value=getFilterResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }

                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }

                });
    }


}