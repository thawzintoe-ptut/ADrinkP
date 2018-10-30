package com.thawzintoe.ptut.adrinkp.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseFragment
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.CategoryFilterActivity
import com.thawzintoe.ptut.adrinkp.adapters.GlassItemsAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.CategoryPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.CategoryView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass
import kotlinx.android.synthetic.main.app_bar_category_item.*
import kotlinx.android.synthetic.main.content_category_item.*

class GlassFragment :
        BaseFragment(),
        CategoryView {
    private val categoryPresenter by lazyAndroid { getViewModel<CategoryPresenter>()}
    private val glassAdapter by lazyAndroid {
        GlassItemsAdapter(context!!, categoryPresenter) }
    private val emptyViewPod  by lazyAndroid{
        emptyLayout as EmptyViewPod }

    override fun launchFilter(itemName: String) {
        startActivity(CategoryFilterActivity.newIntent(context!!, itemName, GLASS_NAME))
//        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        categoryPresenter.errorLD.observe(viewLifecycleOwner,this)
        categoryPresenter.drinkGlassLD!!.observe(viewLifecycleOwner,
                Observer<List<DrinksGlass>> { drinkGlass ->
            swipeRefreshLayout.isRefreshing=false
            glassAdapter.setNewData(drinkGlass as MutableList<DrinksGlass>)
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
        setUpUIComponent()
    }

    private fun setUpUIComponent() {
        toolbar_title.text= GLASS_NAME
        swipeRefreshLayout.isRefreshing=true
        swipeRefreshLayout.refreshingScheme(activity)
        categoryPresenter.initPresenter(this)
        categoryPresenter.onFinishGlass(GLASS_LIST)

        itemRecycler.setUpRecycler(context!!,emptyViewPod)
        itemRecycler.adapter = glassAdapter

        swipeRefreshLayout.setOnRefreshListener {
            categoryPresenter.onFinishGlass(GLASS_LIST)
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