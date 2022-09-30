package com.bhanguz.mvvmhilt.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bhanguz.mvvmhilt.R
import com.bhanguz.mvvmhilt.data.local.ImageEntity
import com.bhanguz.mvvmhilt.databinding.SingleItemImageBinding
import com.bumptech.glide.Glide


class ImagelistAdapter(private val onImageClick : (ImageEntity) -> Unit?) : ListAdapter<ImageEntity, ImagelistAdapter.MyViewHolder>(
    object  : DiffUtil.ItemCallback<ImageEntity>(){
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
           return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SingleItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding,onImageClick={
            onImageClick(getItem(it))
        })
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyViewHolder(private val binding:SingleItemImageBinding,onImageClick: (Int) -> Unit?):RecyclerView.ViewHolder(binding.root) {
        fun setData(item:ImageEntity){
            Glide.with(binding.root.context).load(item.downloader_url)
                .into(binding.imageView)
        }
        init {
            binding.imageView.setOnClickListener {
                onImageClick?.invoke(adapterPosition)
            }
        }
    }
}