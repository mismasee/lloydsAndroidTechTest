package com.lloydtechassignment.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Can show [Toast] from every [Activity].
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

/**
 * Returns Color from resource.
 * @param id Color Resource ID
 */
fun Activity.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)

/**
 * Provides [ViewModel] of type [VM] from [factory].
 */
inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(
    factory: ViewModelProvider.Factory
) = ViewModelProvider(this, factory)[VM::class.java]

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork
    val connection = cm.getNetworkCapabilities(network)
    return connection != null && (
            connection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    connection.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
}