package com.example.ptut.adrinkp.components

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class SmartScrollListener(private val mSmartScrollListener: OnSmartScrollListener) : RecyclerView.OnScrollListener() {

    private var isListEndReached = false

    interface OnSmartScrollListener {
        fun onListEndReach()
    }

    override fun onScrolled(rv: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(rv, dx, dy)

        val visibleItemCount = rv!!.layoutManager.childCount
        val totalItemCount = rv.layoutManager.itemCount
        val pastVisibleItems = (rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (visibleItemCount + pastVisibleItems < totalItemCount) {
            isListEndReached = false
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
        super.onScrollStateChanged(recyclerView, scrollState)
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                && (recyclerView!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == recyclerView.adapter.itemCount - 1
                && !isListEndReached) {
            isListEndReached = true
            mSmartScrollListener.onListEndReach()
        }
    }
}
