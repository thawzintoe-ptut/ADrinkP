package com.example.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.activities.base.BaseActivity
import com.example.ptut.adrinkp.adapters.CategoryFilterAdapter
import com.example.ptut.adrinkp.mvp.presenters.FilterPresenter
import com.example.ptut.adrinkp.mvp.views.FilterView
import kotlinx.android.synthetic.main.activity_filter_view.*
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.example.ptut.adrinkp.utils.*
import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.example.ptut.adrinkp.components.SmartScrollListener
import kotlinx.android.synthetic.main.content_category_item.*


@SuppressLint("Registered")
class CategoryFilterActivity:BaseActivity(),FilterView {
    private lateinit var filterPresenter:FilterPresenter
    private lateinit var categoryFilterAdapter:CategoryFilterAdapter
    private var mSmartScrollListener: SmartScrollListener?=null
    companion object {
        fun newIntent(context: Context,filterName:String,from:String): Intent {
            val intent= Intent(context, CategoryFilterActivity::class.java)
            intent.putExtra(AppConstant.FILTER_NAME,filterName)
            intent.putExtra(AppConstant.Filter_FROM,from)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_view)
        setSupportActionBar(toolbar)
        val filterName:String=intent.getStringExtra(AppConstant.FILTER_NAME)
        toolbar_title.text=filterName
        val fromFilter:String=intent.getStringExtra(AppConstant.Filter_FROM)
        onFinishUI(filterName,fromFilter)

        filterPresenter.categoryFilter!!.observe(this, Observer<List<DrinksCategoryFilter>> {
            getDrinkFilter ->
            swipeRefreshFilter.isRefreshing=false
            categoryFilterAdapter.setNewData(getDrinkFilter as MutableList<DrinksCategoryFilter>)
        })

    }

    private fun onFinishUI(filterName: String,fromFilter:String){
        swipeRefreshFilter.isRefreshing=true
        filterPresenter=ViewModelProviders.of(this@CategoryFilterActivity).get(FilterPresenter::class.java)
        filterPresenter.initPresenter(this@CategoryFilterActivity)
        filterPresenter.errorLD.observe(this,this)
        when(fromFilter){
            AppConstant.CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
            AppConstant.GLASS_NAME ->filterPresenter.onFinishGlassFilter(filterName)
            AppConstant.ALCOHOL_NAME ->filterPresenter.onFinishAlcoholFilter(filterName)
            AppConstant.INGREDIENT_NAME ->filterPresenter.onFinishIngredientFilter(filterName)
        }

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        filterRecycler.layoutManager=layoutManager
        categoryFilterAdapter= CategoryFilterAdapter(applicationContext,filterPresenter)
        filterRecycler.adapter=categoryFilterAdapter

        swipeRefreshFilter.setOnRefreshListener {
            when(fromFilter){
                AppConstant.CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
                AppConstant.GLASS_NAME ->filterPresenter.onFinishGlassFilter(filterName)
                AppConstant.ALCOHOL_NAME ->filterPresenter.onFinishAlcoholFilter(filterName)
                AppConstant.INGREDIENT_NAME ->filterPresenter.onFinishIngredientFilter(filterName)
            }
        }
    }


    override fun launchDetails(item: DrinksCategoryFilter) {
        Toast.makeText(applicationContext,"itemName: $item",Toast.LENGTH_LONG).show()
    }

    override fun onChanged(error: Error?) {
        error?.let {
            when (it) {
                is EmptyError ->{
                    filterEmptyLayout.visibility=View.VISIBLE
                    filterRecycler.setEmptyView(filterEmptyLayout)
                }
                is NetworkError -> UtilGeneral.showNetworkError(filterLayout, applicationContext, error as NetworkError)
            }
        }
    }
}