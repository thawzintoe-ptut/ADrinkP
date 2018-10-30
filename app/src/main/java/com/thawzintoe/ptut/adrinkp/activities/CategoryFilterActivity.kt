package com.thawzintoe.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.widget.ImageView
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.CategoryFilterAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.FilterPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.FilterView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import kotlinx.android.synthetic.main.activity_filter_view.*


@SuppressLint("Registered")
class CategoryFilterActivity : BaseActivity(), FilterView {
    private val filterPresenter by lazyAndroid { getViewModel<FilterPresenter>() }
    private val categoryFilterAdapter by lazyAndroid { CategoryFilterAdapter(applicationContext, filterPresenter) }
    private val emptyViewPod by lazyAndroid {
        filterEmptyLayout as EmptyViewPod
    }

    companion object {
        fun newIntent(context: Context, filterName: String, from: String): Intent {
            val intent = Intent(context, CategoryFilterActivity::class.java)
            intent.putExtra(FILTER_NAME, filterName)
            intent.putExtra(Filter_FROM, from)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_view)
        setSupportActionBar(toolbar)

        val filterName: String = intent.getStringExtra(FILTER_NAME)
        toolbar_title.text = filterName
        val fromFilter: String = intent.getStringExtra(Filter_FROM)
        onFinishUI(filterName, fromFilter)

        filterPresenter.categoryFilter?.observe(this, Observer<List<DrinksCategoryFilter>> { getDrinkFilter ->
            swipeRefreshFilter.isRefreshing = false
            categoryFilterAdapter.setNewData(getDrinkFilter as MutableList<DrinksCategoryFilter>)
        })

    }

    private fun onFinishUI(filterName: String, fromFilter: String) {
        swipeRefreshFilter.isRefreshing = true
        swipeRefreshFilter.refreshingScheme(this)
        filterPresenter.initPresenter(this@CategoryFilterActivity)
        filterPresenter.errorLD.observe(this, this)
        when (fromFilter) {
            CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
            GLASS_NAME -> filterPresenter.onFinishGlassFilter(filterName)
            ALCOHOL_NAME -> filterPresenter.onFinishAlcoholFilter(filterName)
            INGREDIENT_NAME -> filterPresenter.onFinishIngredientFilter(filterName)
        }

        filterRecycler.setUpGrid(applicationContext, emptyViewPod)
        filterRecycler.adapter = categoryFilterAdapter

        swipeRefreshFilter.setOnRefreshListener {
            when (fromFilter) {
                CATEGORY_NAME -> filterPresenter.onFinishCategoryFilter(filterName)
                GLASS_NAME -> filterPresenter.onFinishGlassFilter(filterName)
                ALCOHOL_NAME -> filterPresenter.onFinishAlcoholFilter(filterName)
                INGREDIENT_NAME -> filterPresenter.onFinishIngredientFilter(filterName)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun launchDetails(item: DrinksCategoryFilter, imageView: ImageView) {
        val intent = LookUpCocktailDetail.newIntent(this, item.idDrink!!,item.strDrinkThumb!!)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName)
        startActivity(intent, options.toBundle())
    }

    override fun onChanged(error: Error?) {
        error?.let {
            swipeRefreshFilter.isRefreshing = false
            when (it) {
                is EmptyError -> {
                    emptyViewPod.setEmptyData(R.drawable.empty_img, "Product Not Found")
                }
                is NetworkError -> {
                    emptyViewPod.setEmptyData(R.drawable.nointernet, "No Internet Connection")
                }
            }
        }
    }

    override fun onBackPressed() {
        startActivity(HomeActivity.newIntent(applicationContext, CATEGORY_INDEX))
        overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit)
        finish()

    }
}