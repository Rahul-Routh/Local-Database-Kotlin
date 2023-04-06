package com.example.localdatabasekotlin.Service


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.localdatabasekotlin.Activity.MainActivity


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val mainActivity  = MainActivity()

        try {
            if (isOnline(context)) {

                mainActivity.dialogNetwork(true)
                Log.e("TAG", "Online Connect Intenet ")
            } else {
                mainActivity.dialogNetwork(false)
                Log.e("TAG", "Conectivity Failure !!! ")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }



    fun isOnline(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm?.activeNetworkInfo
        //should check null because in airplane mode it will be null
        //return netInfo != null && netInfo.isConnected
        var up = false
        if (netInfo != null && netInfo.isConnected) {
            up = true
        }

        return up
    }
}