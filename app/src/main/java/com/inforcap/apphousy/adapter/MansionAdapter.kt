package com.inforcap.apphousy.adapter

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.inforcap.apphousy.R
import com.inforcap.apphousy.databinding.ItemMansionBinding
import com.inforcap.apphousy.model.Mansion

class MansionAdapter(
    private val mansionList: List<Mansion>): RecyclerView.Adapter<MansionAdapter.MansionViewHolder>() {

        private lateinit var binding: ItemMansionBinding
        var onClick: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MansionViewHolder {
        binding = ItemMansionBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MansionViewHolder(binding)
    }

    override fun getItemCount(): Int = mansionList.size

    override fun onBindViewHolder(holder: MansionViewHolder, position: Int) {
        holder.onBind(mansionList[position])
    }

    inner class MansionViewHolder(private val binding: ItemMansionBinding) : ViewHolder(binding.root) {
        fun onBind(mansion: Mansion){
            with(binding) {
                mansion.run {
                    textViewNombreMansion.text = mansion.name
                    textViewValorMansion.text = DecimalFormat("$#,###").format(mansion.price)

                    Glide.with(imageMansion.context)
                        .load(mansion.photo)
                        .centerCrop()
                        .error(R.drawable.baseline_error_outline_24)
                        .into(binding.imageMansion)
                }
            }

            binding.clMansion.setOnClickListener {
                onClick?.invoke(mansion.id)
            }
        }
    }
}