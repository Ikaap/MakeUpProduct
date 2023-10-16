package com.ikapurwanti.makeupproduct.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.ikapurwanti.makeupproduct.databinding.ItemMakeUpBinding
import com.ikapurwanti.makeupproduct.model.MakeUpViewParam

class MakeUpListAdapter() : RecyclerView.Adapter<MakeUpItemViewHolder>() {

    private val dataDiffer =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<MakeUpViewParam>() {
            override fun areItemsTheSame(
                oldItem: MakeUpViewParam,
                newItem: MakeUpViewParam
            ): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(
                oldItem: MakeUpViewParam,
                newItem: MakeUpViewParam
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeUpItemViewHolder {
        return MakeUpItemViewHolder(
            binding = ItemMakeUpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MakeUpItemViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    fun setData(data : List<MakeUpViewParam>){
        dataDiffer.submitList(data)
    }

    fun refreshData(){
        notifyItemRangeChanged(0, dataDiffer.currentList.size)
    }

}



class MakeUpItemViewHolder(
    private val binding: ItemMakeUpBinding
) : ViewHolder(binding.root) {

    fun bind(makeUp: MakeUpViewParam) {
        with(binding) {
            ivMakeupImage.load(makeUp.imageLink)
            tvMakeupName.text = makeUp.name
            tvMakeupDesc.text = makeUp.description
            tvMakeupPrice.text = "IDR ${makeUp.price}"
        }
    }

}