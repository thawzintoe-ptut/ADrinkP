package com.thawzintoe.ptut.adrinkp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseFragment
import com.thawzintoe.ptut.adrinkp.Injection
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.CategoryFilterActivity
import com.thawzintoe.ptut.adrinkp.adapters.AlcoholItemsAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.CategoryPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.CategoryView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksAlcohol
import kotlinx.android.synthetic.main.app_bar_category_item.*
import kotlinx.android.synthetic.main.content_category_item.*

class AlcoholFragment :
        BaseFragment(),
        CategoryView {
    private val categoryPresenter by lazyAndroid {
        ViewModelProviders.of(this,
                Injection.provideViewModelFactoryCategory(CategoryPresenter()))
                .get(CategoryPresenter::class.java)
    }
    private val alcoholAdapter by lazyAndroid {
        AlcoholItemsAdapter(context!!, categoryPresenter) }
    private val emptyViewPod  by lazyAndroid{
        emptyLayout as EmptyViewPod }

    override fun launchFilter(itemName: String) {
        startActivity(CategoryFilterActivity.newIntent(context!!, itemName, ALCOHOL_NAME))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        categoryPresenter.errorLD.observe(viewLifecycleOwner,this)
        categoryPresenter.drinkAlcoholLD!!.observe(viewLifecycleOwner,
                Observer<List<DrinksAlcohol>> { drinkIngredient ->
            swipeRefreshLayout.isRefreshing = false
            alcoholAdapter.setNewData(drinkIngredient as MutableList<DrinksAlcohol>)
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.activity_category_item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_title.text = ALCOHOL_NAME
        setUpUIComponent()
    }

    private fun setUpUIComponent() {
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.refreshingScheme(activity)
        categoryPresenter.initPresenter(this)
        categoryPresenter.onFinishAlcohol("list")

        itemRecycler.setUpRecycler(context!!,emptyViewPod)
        itemRecycler.adapter = alcoholAdapter

        swipeRefreshLayout.setOnRefreshListener {
            categoryPresenter.onFinishAlcohol("list")
        }
    }

    override fun onChanged(error: Error?) {
        error?.let {
            swipeRefreshLayout.isRefreshing = false
            when (it) {
                is EmptyError -> {
                    emptyViewPod.setEmptyData(
                            R.drawable.empty_img,
                            "Product Not Found")
                }
                is NetworkError ->{
                    emptyViewPod.setEmptyData(
                            R.drawable.nointernet,
                            "No Internet Connection")
                }
            }
        }
    }

}