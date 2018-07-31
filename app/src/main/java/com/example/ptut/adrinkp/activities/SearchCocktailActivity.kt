package com.example.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.activities.base.BaseActivity
import com.example.ptut.adrinkp.adapters.SearchCocktailAdapter
import com.example.ptut.adrinkp.components.SmartScrollListener
import com.example.ptut.adrinkp.mvp.presenters.SearchPresenter
import com.example.ptut.adrinkp.mvp.views.SearchView
import com.example.ptut.adrinkp.utils.EmptyError
import com.example.ptut.adrinkp.utils.Error
import com.example.ptut.adrinkp.utils.NetworkError
import com.example.ptut.adrinkp.utils.UtilGeneral
import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_category_item.*


@SuppressLint("Registered")
class SearchCocktailActivity : BaseActivity(), SearchView, View.OnClickListener {

    private lateinit var searchPresenter: SearchPresenter
    private lateinit var searchCocktailAdapter: SearchCocktailAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCocktailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchEdit.hint = resources.getString(R.string.searchCocktailHint)
        setUpUIComponent()
        searchEdit.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode === KeyEvent.KEYCODE_ENTER && event.action === KeyEvent.ACTION_DOWN) {
                swipeContainer.isRefreshing = true
                searchPresenter.onNotifySearch(searchEdit.text.toString())
                swipeContainer.setOnRefreshListener {
                    searchPresenter.onNotifySearch(searchEdit.text.toString())
                }
                true
            } else {
                ivBack.visibility=View.VISIBLE
                true
            }
        }
        searchPresenter.errorLD.observe(this, this@SearchCocktailActivity)
        searchPresenter.ingredientLD!!.observe(this, Observer<List<SearchDrinksItem>> { getIngredientsItem: List<SearchDrinksItem>? ->
            swipeContainer.isRefreshing = false
            searchCocktailAdapter.setNewData(getIngredientsItem as MutableList<SearchDrinksItem>)
        })
    }

    private fun setUpUIComponent() {
        searchPresenter = ViewModelProviders.of(this).get(SearchPresenter::class.java)
        searchPresenter.initPresenter(this@SearchCocktailActivity)
        searchRecycler.layoutManager = LinearLayoutManager(this@SearchCocktailActivity)
        searchCocktailAdapter = SearchCocktailAdapter(this@SearchCocktailActivity, searchPresenter)
        searchRecycler.adapter = searchCocktailAdapter
        ivBack.setOnClickListener(this@SearchCocktailActivity)
    }

    override fun onChanged(error: Error?) {
        error?.let {
            when (it) {
                is EmptyError -> {
                    swipeContainer.isRefreshing = false
                    searchEmptyLayout.visibility=View.VISIBLE
                    searchRecycler.setEmptyView(searchEmptyLayout)
                }
                is NetworkError -> UtilGeneral.showNetworkError(searchLayout, applicationContext, error as NetworkError)
            }
        }
    }
    override fun onClick(v: View?) {
        startActivity(CategoryItemActivity.newIntent(applicationContext))
        finish()
    }

    override fun lunchSearchDetailView(searchDrinksItem: SearchDrinksItem) {
        startActivity(SearchCocktailDetailActivity.newIntent(applicationContext,searchDrinksItem))
    }



}