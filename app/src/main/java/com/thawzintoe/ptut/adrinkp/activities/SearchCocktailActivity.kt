package com.thawzintoe.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.base.BaseActivity
import com.thawzintoe.ptut.adrinkp.adapters.SearchCocktailAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.SearchPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.SearchView
import com.thawzintoe.ptut.adrinkp.utils.EmptyError
import com.thawzintoe.ptut.adrinkp.utils.Error
import com.thawzintoe.ptut.adrinkp.utils.NetworkError
import com.thawzintoe.ptut.adrinkp.utils.setUpRecycler
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem
import kotlinx.android.synthetic.main.activity_search.*


@SuppressLint("Registered")
class SearchCocktailActivity : BaseActivity(), SearchView, View.OnClickListener {

    private  val searchPresenter: SearchPresenter by lazy{
        ViewModelProviders.of(this).get(SearchPresenter::class.java)
    }
    private val searchCocktailAdapter: SearchCocktailAdapter by lazy{
        SearchCocktailAdapter(this@SearchCocktailActivity, searchPresenter)
    }
    private val emptyViewPod: EmptyViewPod? by lazy{
        searchEmptyLayout as EmptyViewPod
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCocktailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchEdit.hint = resources.getString(R.string.searchCocktailHint)
        initSearch()
        setUpUIComponent()
        searchClear.setOnClickListener(this)
        searchPresenter.errorLD.observe(this, this@SearchCocktailActivity)
        searchPresenter.ingredientLD?.observe(this, Observer<List<SearchDrinksItem>> {
            swipeContainer.isRefreshing = false
            searchCocktailAdapter.setNewData(it as MutableList<SearchDrinksItem>)
        })
    }

    private fun setUpUIComponent() {
        searchPresenter.initPresenter(this)

        searchRecycler.setUpRecycler(applicationContext,emptyViewPod!!)
        searchRecycler.adapter = searchCocktailAdapter

        ivBack.setOnClickListener(this@SearchCocktailActivity)
    }

    override fun onChanged(error: Error?) {
        error?.let {
            swipeContainer.isRefreshing = false
            when (it) {
                is EmptyError -> {
                    emptyViewPod!!.setEmptyData(R.drawable.empty_img, "Product Not Found")
                }
                is NetworkError -> {
                    emptyViewPod!!.setEmptyData(R.drawable.nointernet, "No Internet Connection")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                startActivity(CategoryItemActivity.newIntent(applicationContext))
                finish()
            }
            R.id.searchClear -> {
                searchEdit.text.clear()
            }
        }
    }

    private fun initSearch() {
        searchEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                true
            } else {
                updateRepoListFromInput()
                false
            }
        }
        searchEdit.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun updateRepoListFromInput() {
        val text = searchEdit.text.toString()
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        swipeContainer.isRefreshing = true
        searchCocktailAdapter.clearData()
        searchPresenter.onNotifySearch(text)
        swipeContainer.setOnRefreshListener {
            searchPresenter.onNotifySearch(text)
        }
        ivBack.visibility = View.VISIBLE
    }

    override fun lunchSearchDetailView(searchDrinksItem: SearchDrinksItem, imageView: ImageView) {
        val intent = SearchCocktailDetailActivity.newIntent(applicationContext, searchDrinksItem)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "strImage")
        startActivity(intent, options.toBundle())
    }

}