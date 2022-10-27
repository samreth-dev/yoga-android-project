package com.example.yoga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.yoga_type_list.view.*

class MyAdapter(var ylist: ArrayList<Yoga>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var onItemClick: ((Yoga) -> Unit)? = null

    // Inflate the Layout to set in the RecyclerView and return the ViewHolder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.yoga_type_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val yoga = ylist[position]
        holder.itemView.yogaTitle.text = yoga.title
        holder.itemView.yogaDetails.text = yoga.details
        //Alternating colors
        if (position % 2 == 0) {
            holder.itemView.yogaTypeList.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                 R.color.app_primary_color
                )
            )
        } else {
            holder.itemView.yogaTypeList.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
        }
    }


    override fun getItemCount(): Int {
        return ylist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(ylist[adapterPosition])
            }
        }
    }
}
