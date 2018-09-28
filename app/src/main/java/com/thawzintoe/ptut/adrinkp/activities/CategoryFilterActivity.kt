package com.thawzintoe.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.base.BaseActivity
import com.thawzintoe.ptut.adrinkp.adapters.CategoryFilterAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.FilterPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.FilterView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import kotlinx.android.synthetic.main.activity_filter_view.*


@SuppressLint("Registered")
class CategoryFilterActivity:BaseActivity(),FilterView {
    private lateinit var filterPresenter:FilterPresenter
    private val categoryFilterAdapter by lazy { CategoryFilterAdapter(applicationContext,filterPresenter) }
    private val layoutManager by lazy { LinearLayoutManager(this) }
    private var emptyViewPod:EmptyViewPod?=null
    companion object {
        fun newIntent(context: Context,filterName:String,from:String): Intent {
            val intent= Intent(context, CategoryFilterActivity::class.java)
            intent.putExtra(FILTER_NAME,filterName)
            intent.putExtra(Filter_FROM,from)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_view)
        setSupportActionBar(toolbar)
        val filterName:String=intent.getStringExtra(FILTER_NAME)
        toolbar_title.text=filterName
        val fromFilter:String=intent.getStringExtra(Filter_FROM)
        onFinishUI(filterName,fromFilter)

        filterPresenter.categoryFilter?.observe(this, Observer<List<DrinksCategoryFilter>> {
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
            CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
            GLASS_NAME ->filterPresenter.onFinishGlassFilter(filterName)
            ALCOHOL_NAME ->filterPresenter.onFinishAlcoholFilter(filterName)
            INGREDIENT_NAME ->filterPresenter.onFinishIngredientFilter(filterName)
        }

        filterRecycler.layoutManager=layoutManager
        filterRecycler.adapter=categoryFilterAdapter

        swipeRefreshFilter.setOnRefreshListener {
            when(fromFilter){
                CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
                GLASS_NAME ->filterPresenter.onFinishGlassFilter(filterName)
                ALCOHOL_NAME ->filterPresenter.onFinishAlcoholFilter(filterName)
                INGREDIENT_NAME ->filterPresenter.onFinishIngredientFilter(filterName)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun launchDetails(item: DrinksCategoryFilter, imageView: ImageView) {
        val intent=LookUpCocktailDetail.newIntent(this,item.idDrink!!)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName)
        startActivity(intent,options.toBundle())
    }

    override fun onChanged(error: Error?) {
        error?.let {
            when (it) {
                is EmptyError -> {
                    swipeRefreshFilter.isRefreshing=false
                    emptyViewPod=filterEmptyLayout as EmptyViewPod
                    emptyViewPod!!.setEmptyData(R.drawable.empty_img,"Product Not Found")
                    filterEmptyLayout.setBackgroundColor(resources.getColor(R.color.white))
                    filterEmptyLayout.visibility = View.VISIBLE
                    filterRecycler.setEmptyView(filterEmptyLayout)
                }
                is NetworkError ->{
                    swipeRefreshFilter.isRefreshing=false
                    emptyViewPod=filterEmptyLayout as EmptyViewPod
                    filterEmptyLayout.setBackgroundColor(resources.getColor(R.color.white))
                    emptyViewPod!!.setEmptyData(R.drawable.nointernet,"No Internet Connection")
                    filterEmptyLayout.visibility = View.VISIBLE
                    filterRecycler.setEmptyView(emptyViewPod!!)
                    showNetworkError(drawer_layout, applicationContext, error as NetworkError)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit)
    }
}