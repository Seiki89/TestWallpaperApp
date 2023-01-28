package com.seiki.android.wallpaperapptest.screens.main_fragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.wallpaperapptest.R
import com.seiki.android.wallpaperapptest.model.Buttons


class CategoryAdapter (
    private val list: MutableList<Buttons>,
    private val mainFragment: MainFragment
    ): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val btn : Button = itemView.findViewById(R.id.btnAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.btn.text = list[position].btn
        holder.btn.setOnClickListener {
            mainFragment.clickButtonAdapter(list[position].req)
        }
    }

    override fun getItemCount() = list.size

}
