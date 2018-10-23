package com.thawzintoe.ptut.adrinkp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.components.EmptyViewPod
import com.thawzintoe.ptut.adrinkp.components.SmartRecyclerView
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

val threadCt = Runtime.getRuntime().availableProcessors() + 1
val executor = Executors.newFixedThreadPool(threadCt)!!
val scheduler = Schedulers.from(executor)




inline fun  SmartRecyclerView.setUpRecycler(context: Context,emptyViewPod: EmptyViewPod){
    hasFixedSize()
    layoutManager=LinearLayoutManager(context)
    setEmptyView(emptyViewPod)
}

fun randomColor(view: View) {
    val androidColors = view.resources.getIntArray(R.array.androidcolors)
    val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
    view.setBackgroundColor(randomAndroidColor)
}

@SuppressLint("SimpleDateFormat")
fun prettyTime(string: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd kk:mm:ss")
    val date = dateFormat.parse(string)
    return "$date"
}


fun profileDialog(context: Context, title: String, desc: String) {
    MaterialStyledDialog.Builder(context)
            .setTitle(title)
            .setDescription(desc)
            .setIcon(R.drawable.ic_appicon)
            .setHeaderDrawable(R.color.midnightblue)
            .withIconAnimation(true)
            .setPositiveText("Dismiss")
            .show()
}
