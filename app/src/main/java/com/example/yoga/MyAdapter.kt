package com.example.yoga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.yoga_type_list.view.*

class MyAdapter(var ylist: ArrayList<Yoga>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // Inflate the Layout to set in the RecyclerView and return the ViewHolder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.yoga_type_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val yoga = ylist[position]
        holder.itemView.imageView.setImageResource(yoga.image)
        holder.itemView.yogaTitle.text = yoga.title
        holder.itemView.yogaDetails.text = yoga.details
    }


    override fun getItemCount(): Int {
        return ylist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
