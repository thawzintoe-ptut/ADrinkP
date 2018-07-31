package com.example.ptut.adrinkp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.activities.base.BaseActivity
import com.example.ptut.adrinkp.adapters.AlcoholItemsAdapter
import com.example.ptut.adrinkp.components.SmartScrollListener
import com.example.ptut.adrinkp.mvp.presenters.CategoryPresenter
import com.example.ptut.adrinkp.mvp.views.CategoryView
import com.example.ptut.adrinkp.utils.*
import com.example.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import kotlinx.android.synthetic.main.activity_category_item.*
import kotlinx.android.synthetic.main.app_bar_category_item.*
import kotlinx.android.synthetic.main.content_category_item.*

@SuppressLint("Registered")
class AlcoholItemActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, CategoryView {
    private lateinit var alcoholAdapter: AlcoholItemsAdapter
    private lateinit var categoryPresenter: CategoryPresenter
    private var mSmartScrollListener: SmartScrollListener? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AlcoholItemActivity::class.java)
        }
    }

    override fun launchFilter(itemName: String) {
        startActivity(CategoryFilterActivity.newIntent(applicationContext, itemName, AppConstant.ALCOHOL_NAME))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_item)
        toolbar_title.text = AppConstant.ALCOHOL_NAME
        setSupportActionBar(toolbar)
        setUpUIComponent()
        navigateTool()
        categoryPresenter.drinkAlcoholLD!!.observe(this, Observer<List<DrinksAlcohol>> { drinkIngredient ->
            swipeRefreshLayout.isRefreshing = false
            alcoholAdapter.setNewData(drinkIngredient as MutableList<DrinksAlcohol>)
        })
    }

    private fun setUpUIComponent() {
        swipeRefreshLayout.isRefreshing = true
        categoryPresenter = ViewModelProviders.of(this).get(CategoryPresenter::class.java)
        categoryPresenter.initPresenter(this@AlcoholItemActivity)
        categoryPresenter.onFinishAlcohol("list")
        categoryPresenter.errorLD.observe(this, this@AlcoholItemActivity)

        itemRecycler.layoutManager = LinearLayoutManager(applicationContext)
        alcoholAdapter = AlcoholItemsAdapter(applicationContext, categoryPresenter)
        itemRecycler.adapter = alcoholAdapter
        mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener {
            override fun onListEndReach() {
                categoryPresenter.onFinishAlcohol("list")
            }
        })
        itemRecycler.addOnScrollListener(mSmartScrollListener)
        swipeRefreshLayout.setOnRefreshListener {
            categoryPresenter.onFinishAlcohol("list")
        }
    }

    private fun navigateTool() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.category_item, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_category -> {
                startActivity(CategoryItemActivity.newIntent(this))
                finish()
            }
            R.id.nav_glass -> {
                startActivity(GlassItemActivity.newIntent(this))
                finish()
            }
            R.id.nav_ingredient -> {
                startActivity(IngredientItemActivity.newIntent(this))
                finish()
            }
            R.id.nav_alcohol -> {
                startActivity(AlcoholItemActivity.newIntent(this))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onChanged(error: Error?) {
        error?.let {
            when (it) {
                is EmptyError -> {
                    emptyLayout.visibility = View.VISIBLE
                    itemRecycler.setEmptyView(emptyLayout)
                }
                is NetworkError -> UtilGeneral.showNetworkError(rootLayout, applicationContext, error as NetworkError)
            }
        }
    }

}