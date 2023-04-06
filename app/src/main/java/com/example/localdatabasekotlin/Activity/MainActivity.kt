package com.example.localdatabasekotlin.Activity

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.localdatabasekotlin.Model.User
import com.example.localdatabasekotlin.R
import com.example.localdatabasekotlin.Service.NetworkChangeReceiver
import com.example.localdatabasekotlin.databinding.ActivityMainBinding
import com.example.localdatabasekotlin.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mUserViewModel: UserViewModel

    private lateinit var mNetworkReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       // mNetworkReceiver = NetworkChangeReceiver()
        //registerNetworkBroadcast()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.saveBtn.setOnClickListener {
            dataInsertInDatabase()
        }
        binding.listBtn.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        //dialog(false)
    }

    private fun registerNetworkBroadcast() {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun dataInsertInDatabase(){
        val enterFirstName = binding.enterFirstName.text.toString()
        val enterLastName = binding.enterLastName.text.toString()
        val enterAge = binding.enterAge.text

        if(inputCheck(enterFirstName, enterLastName, enterAge)) {
            val user = User(0, enterFirstName, enterLastName, Integer.parseInt(enterAge.toString()))

            mUserViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    fun dialogNetwork(value: Boolean) {
        Log.e("sdsdas", "sdsadsad")
        if (value) {
            binding.tvCheckConnection.text = "We are back !!!"
            binding.tvCheckConnection.setBackgroundColor(Color.GREEN)
            binding.tvCheckConnection.setTextColor(Color.BLACK)
            val handler = Handler()
            val delayrunnable = Runnable {
                binding.tvCheckConnection.visibility = View.GONE
            }
            handler.postDelayed(delayrunnable, 3000)
        } else {
            binding.tvCheckConnection.visibility = View.VISIBLE
            binding.tvCheckConnection.text = "Could not Connect to internet"
            binding.tvCheckConnection.setBackgroundColor(Color.RED)
            binding.tvCheckConnection.setTextColor(Color.WHITE)
        }
    }

}