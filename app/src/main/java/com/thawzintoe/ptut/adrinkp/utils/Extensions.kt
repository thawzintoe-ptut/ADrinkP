package com.thawzintoe.ptut.adrinkp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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


fun  SmartRecyclerView.setUpRecycler(context: Context,emptyViewPod: EmptyViewPod){
    hasFixedSize()
    layoutManager=LinearLayoutManager(context)
    val ctx = this.context
    val controller =
            AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_falldown)
    layoutAnimation = controller
    adapter?.notifyDataSetChanged()
    scheduleLayoutAnimation()
    setEmptyView(emptyViewPod)
}

fun  SmartRecyclerView.setUpGrid(context: Context,emptyViewPod: EmptyViewPod){
    hasFixedSize()
    layoutManager=GridLayoutManager(context,2)
    val ctx = this.context
    val controller =
            AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_falldown)
    layoutAnimation = controller
    adapter?.notifyDataSetChanged()
    scheduleLayoutAnimation()
    setEmptyView(emptyViewPod)
}

fun SwipeRefreshLayout.refreshingScheme(activity:Activity?){
    setColorSchemeColors(
            ContextCompat.getColor(activity!!, R.color.md_700_bluegrey),
            ContextCompat.getColor(activity, R.color.blue),
            ContextCompat.getColor(activity, R.color.teal))
}

fun <T> lazyAndroid(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}


fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(this.context).inflate(layoutId, this, false)


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

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack(null).commit()
}


fun AppCompatActivity.replaceFragment( frameId: Int,fragment: Fragment) {

    supportFragmentManager.inTransaction{ replace(frameId, fragment)}
}

val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun  SimpleDateFormat.formatOf(string: String):SimpleDateFormat{
    return SimpleDateFormat("MMM d, yyyy", Locale.US)
}
