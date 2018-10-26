package com.thawzintoe.ptut.adrinkp.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem
import com.thawzintoe.ptut.adrinkp.models.base.BaseModel
import com.thawzintoe.ptut.adrinkp.network.response.GetAlcoholResponse
import com.thawzintoe.ptut.adrinkp.network.response.GetCategoryResponse
import com.thawzintoe.ptut.adrinkp.network.response.GetGlassResponse
import com.thawzintoe.ptut.adrinkp.network.response.GetIngredientResponse
import com.thawzintoe.ptut.adrinkp.utils.EmptyError
import com.thawzintoe.ptut.adrinkp.utils.Error
import com.thawzintoe.ptut.adrinkp.utils.NetworkError
import com.thawzintoe.ptut.adrinkp.utils.scheduler
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ItemListModel private constructor(context: Context) : BaseModel() {
    companion object {
        private var INSTANCE: ItemListModel? = null
        fun getInstance(): ItemListModel {
            if (INSTANCE == null) {
                throw RuntimeException("ItemListModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }
        fun initModel(context: Context) {
            INSTANCE = ItemListModel(context)
        }
    }


    fun getDrinkCategoryList(itemName:String,mDrinkItemLD: MutableLiveData<List<DrinksItem>>, mErrorLD: MutableLiveData<Error>) {
        mTheApi.getCategoryList(itemName)
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetCategoryResponse> {
                    override fun onComplete() {

                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onNext(getCategoryResponse: GetCategoryResponse) {
                        if(getCategoryResponse.drinks!!.isNotEmpty()){
                            mDrinkItemLD.value=getCategoryResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }
                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }
                })
    }
    fun getDrinkGlassList(itemName:String,mDrinkItemLD: MutableLiveData<List<DrinksGlass>>, mErrorLD: MutableLiveData<Error>) {
        mTheApi.getGlassList(itemName)
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetGlassResponse> {
                    override fun onComplete() {

                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onNext(getGlassResponse: GetGlassResponse) {
                        if(getGlassResponse.drinks!!.isNotEmpty() && getGlassResponse!=null){
                            mDrinkItemLD.value=getGlassResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }
                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }
                })
    }
    fun getIngredientList(itemName:String,mDrinkItemLD: MutableLiveData<List<DrinksIngredient>>, mErrorLD: MutableLiveData<Error>) {
        mTheApi.getIngredientList(itemName)
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetIngredientResponse> {
                    override fun onComplete() {

                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onNext(getIngredientResponse: GetIngredientResponse) {
                        if(getIngredientResponse.drinks!!.isNotEmpty() && getIngredientResponse!=null){
                            mDrinkItemLD.value=getIngredientResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }
                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }
                })
    }
    fun getAlcoholList(itemName:String,mDrinkItemLD: MutableLiveData<List<DrinksAlcohol>>, mErrorLD: MutableLiveData<Error>) {
        mTheApi.getAlcoholList(itemName)
                .subscribeOn(scheduler)
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<GetAlcoholResponse> {
                    override fun onComplete() {

                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onNext(getAlcoholResponse: GetAlcoholResponse) {
                        if(getAlcoholResponse.drinks!!.isNotEmpty() && getAlcoholResponse!=null){
                            mDrinkItemLD.value=getAlcoholResponse.drinks
                        }else{
                            mErrorLD.value=EmptyError("No Data")
                        }
                    }
                    override fun onError(e: Throwable) {
                        mErrorLD.value=NetworkError(e.message!!)
                    }
                })
    }
}