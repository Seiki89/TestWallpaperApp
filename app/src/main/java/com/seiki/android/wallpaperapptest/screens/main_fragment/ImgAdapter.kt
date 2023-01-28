package com.seiki.android.wallpaperapptest.screens.main_fragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.wallpaperapptest.R
import com.seiki.android.wallpaperapptest.model.Hit
import com.squareup.picasso.Picasso

class ImgAdapter(
    private val wallList: MutableList<Hit>,
    private val mainFragment: MainFragment
    ) : RecyclerView.Adapter<ImgAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imgAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.img_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(wallList[position].previewURL).into(holder.image)
        holder.image.setOnClickListener {
            mainFragment.clickImgAdapter(wallList[position].largeImageURL)
        }
    }

    override fun getItemCount() = wallList.size

}