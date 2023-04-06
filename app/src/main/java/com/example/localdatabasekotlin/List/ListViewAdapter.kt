package com.example.localdatabasekotlin.List

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.localdatabasekotlin.Activity.ListActivity
import com.example.localdatabasekotlin.Activity.MainActivity
import com.example.localdatabasekotlin.Activity.UpdateActivity
import com.example.localdatabasekotlin.Model.User
import com.example.localdatabasekotlin.R
import com.example.localdatabasekotlin.databinding.ActivityMainBinding
import com.example.localdatabasekotlin.databinding.ListViewSeeBinding


class ListViewAdapter(val listActivity: ListActivity) : RecyclerView.Adapter<ListViewAdapter.MyViewHolder>() {

    private lateinit var binding : ListViewSeeBinding
    private var userList = emptyList<User>()

    class MyViewHolder(val binding: ListViewSeeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_view_see, parent, false)
        return MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.list_view_see, parent, false)
        binding

        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        with(holder){
            with(binding){
                idTxt.setText(currentItem.id.toString())
                firstNameTxt.setText(currentItem.firstName)
                lastNameTxt.setText(currentItem.lastName)
                ageTxt.setText(currentItem.age.toString())

                rowLayout.setOnClickListener {
                    val intent = Intent(listActivity, UpdateActivity::class.java)
                    intent.putExtra("user_id", currentItem.id.toString())
                    intent.putExtra("first_name", currentItem.firstName)
                    intent.putExtra("last_name", currentItem.lastName)
                    intent.putExtra("age", currentItem.age.toString())
                    listActivity.startActivity(intent)
                }
            }
        }

//        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
//        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = currentItem.firstName
//        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItem.lastName
//        holder.itemView.findViewById<TextView>(R.id.age_txt).text = currentItem.age.toString()
//
//        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener{
//
//            val intent = Intent(listActivity, UpdateActivity::class.java)
//            intent.putExtra("first_name", currentItem.firstName)
//            intent.putExtra("last_name", currentItem.lastName)
//            intent.putExtra("age", currentItem.age)
//            listActivity.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}