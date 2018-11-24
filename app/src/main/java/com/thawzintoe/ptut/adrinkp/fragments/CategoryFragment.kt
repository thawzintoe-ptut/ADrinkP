package com.thawzintoe.ptut.adrinkp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import com.mmgoogleexpert.ptut.shared.ui.BaseFragment
import com.thawzintoe.ptut.adrinkp.Injection
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.activities.CategoryFilterActivity
import com.thawzintoe.ptut.adrinkp.adapters.CategoryItemsAdapter
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.mvp.presenters.CategoryPresenter
import com.thawzintoe.ptut.adrinkp.mvp.views.CategoryView
import com.thawzintoe.ptut.adrinkp.utils.*
import com.thawzintoe.ptut.adrinkp.utils.ADrinkPApp.Companion.mFirebaseAnalytics
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem
import kotlinx.android.synthetic.main.app_bar_category_item.*
import kotlinx.android.synthetic.main.content_category_item.*


class CategoryFragment :
        BaseFragment(),
        CategoryView {
    private val categoryPresenter by lazyAndroid {
        ViewModelProviders.of(this,
                Injection.provideViewModelFactoryCategory(CategoryPresenter()))
                .get(CategoryPresenter::class.java)
    }
    private  val categoryAdapter by lazyAndroid{
        CategoryItemsAdapter(context!!, categoryPresenter) }
    private val emptyViewPod  by lazyAndroid{
        emptyLayout as EmptyViewPod }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        categoryPresenter.errorLD.observe(viewLifecycleOwner, this)
        categoryPresenter.drinkLD?.observe(viewLifecycleOwner, Observer<List<DrinksItem>> {
            swipeRefreshLayout.isRefreshing = false
            categoryAdapter.setNewData(it as MutableList<DrinksItem>)
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setUpAnalytics("001","CategoryFragment","image")
        return container?.inflate(R.layout.activity_category_item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUIComponent()
        mFirebaseAnalytics.setCurrentScreen(activity!!, "CurrentScreen: " + javaClass.simpleName, null)
    }


    private fun setUpUIComponent() {
        toolbar_title.text= CATEGORY_NAME
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.refreshingScheme(activity)
        categoryPresenter.initPresenter(this)
        categoryPresenter.onFinishCategory("list")

        itemRecycler.setUpRecycler(context!!,emptyViewPod)
        itemRecycler.adapter = categoryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            categoryPresenter.onFinishCategory("list")
        }
    }


    override fun launchFilter(itemName: String) {
        val intent = CategoryFilterActivity.
                newIntent(
                        context!!,
                        itemName,
                        CATEGORY_NAME)
        startActivity(intent)

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
