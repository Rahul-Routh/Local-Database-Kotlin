package com.example.localdatabasekotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.localdatabasekotlin.Model.User
import com.example.localdatabasekotlin.R
import com.example.localdatabasekotlin.databinding.ActivityMainBinding
import com.example.localdatabasekotlin.databinding.ActivityUpdateBinding
import com.example.localdatabasekotlin.viewModel.UserViewModel

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateBinding

    private lateinit var mUserViewModel: UserViewModel

    var userIdIn: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_update)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        var userId = intent.getStringExtra("user_id")
        var firstName = intent.getStringExtra("first_name")
        val lastName = intent.getStringExtra("last_name")
        val age = intent.getStringExtra("age")
        if (userId != null) {
            userIdIn += userId.toInt()
        }

        Log.d("userIdIn","userIdIn: "+userIdIn)
        // editText Editable
        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

        binding.updateEnterFirstName.text= firstName.toString().toEditable()
        binding.updateEnterLastName.text= lastName.toString().toEditable()
        binding.updateEnterAge.text= age.toString().toEditable()

        binding.updateBtn.setOnClickListener {
            updateItem()
        }
    }

    private fun updateItem() {
        val firstName = binding.updateEnterFirstName.text.toString()
        val lastName = binding.updateEnterLastName.text.toString()
        // val age = binding.updateAgeEt.text.toString() // <- Error : Type mismatch. Required: Int , Found: String.
        val age = Integer.parseInt(binding.updateEnterAge.text.toString()) // Parses a string returns an integer.

        if (inputCheck(firstName, lastName, binding.updateEnterAge.text)) {

            val updatedUser = User(userIdIn, firstName, lastName, age)

            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all fields !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}