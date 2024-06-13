package com.example.calculator.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.ElementItemBinding
import com.example.calculator.presentation.Constants

class ButtonItemAdapter : RecyclerView.Adapter<ButtonItemViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonItemViewHolder {
        val binding = ElementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Constants.SYMBOLS_ARRAY.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (Constants.SYMBOLS_ARRAY[position] == "=") 2 else 1
    }

    override fun onBindViewHolder(holder: ButtonItemViewHolder, position: Int) {
        holder.binding.itemButton.text = Constants.SYMBOLS_ARRAY[position]
        setButtonsColors(holder, position)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(Constants.SYMBOLS_ARRAY[position])
        }
    }

    private fun setButtonsColors(holder: ButtonItemViewHolder, position: Int) {
        val context = holder.itemView.context
        when (position) {
            0 -> {
                with(holder.binding) {
                    itemButton.setTextColor(ContextCompat.getColor(context, R.color.dark_grey))
                    itemButton.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
            }
            2, 3, 7, 11, 15 -> {
                with(holder.binding) {
                    itemButton.setTextColor(ContextCompat.getColor(context, R.color.green))
                    itemButton.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                }
            }
            18, 20 -> {
                with(holder.binding) {
                    itemButton.setTextColor(ContextCompat.getColor(context, R.color.dark_grey))
                    itemButton.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
            }
            else -> {
                with(holder.binding) {
                    itemButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                    itemButton.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_grey))
                }
            }
        }
    }
}