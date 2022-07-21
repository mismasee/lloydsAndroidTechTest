package com.lloydtechassignment.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

/**
 * Can show [Toast] from every [Activity].
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}



fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork
    val connection = cm.getNetworkCapabilities(network)
    return connection != null && (
            connection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    connection.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
}