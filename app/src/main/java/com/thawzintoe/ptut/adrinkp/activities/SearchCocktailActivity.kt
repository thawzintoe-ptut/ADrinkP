package com.thawzintoe.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.SearchCocktailAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.SearchPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.SearchView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem
import android.view.ViewTreeObserver
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import com.thawzintoe.ptut.adrinkp.Injection
import kotlinx.android.synthetic.main.activity_filter_view.*
import kotlinx.android.synthetic.main.activity_search.*


@SuppressLint("Registered")
open class SearchCocktailActivity : BaseActivity(), SearchView, View.OnClickListener {

    var rootLayout: View? = null
    private var revealX: Int = 0
    private var revealY: Int = 0

    private  val searchPresenter: SearchPresenter by lazyAndroid{
      ViewModelProviders.of(this,
              Injection.provideViewModelSearch(SearchPresenter()))
              .get(SearchPresenter::class.java)
    }
    private val searchCocktailAdapter: SearchCocktailAdapter by lazyAndroid{
        SearchCocktailAdapter(this@SearchCocktailActivity, searchPresenter)
    }
    private val emptyViewPod: EmptyViewPod? by lazyAndroid{
        searchEmptyLayout as EmptyViewPod
    }

    companion object {
        const val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
        const val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchCocktailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val intent = intent

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            searchLayout.visibility = View.INVISIBLE
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            val viewTreeObserver = searchLayout.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        searchLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            searchLayout.visibility = View.VISIBLE
        }

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
                startActivity(HomeActivity.newIntent(applicationContext, CATEGORY_INDEX))
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
        imm.hideSoftInputFromWindow(searchEdit.windowToken, 0)
        swipeContainer.isRefreshing = true
        swipeContainer.refreshingScheme(this)
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

    open fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(searchLayout.width, searchLayout.height) * 1.1).toFloat()

            // create the animator for this view (the start radius is zero)
            val circularReveal = ViewAnimationUtils.createCircularReveal(searchLayout, x, y, 0f, finalRadius)
            circularReveal.duration = 400
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            searchLayout.visibility = View.VISIBLE
            circularReveal.start()
        } else {
            finish()
        }
    }


}