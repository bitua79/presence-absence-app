package com.application.presence_absence.ui.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemCheckBoxBinding

class CheckBoxListAdapter(
    private val onItemChecked: (exam: CheckBoxItemView) -> Unit,
    private val onItemUnChecked: (exam: CheckBoxItemView) -> Unit

) : ListAdapter<CheckBoxItemView, CheckBoxListAdapter.CheckBoxItemViewHolder>(

    object : DiffUtil.ItemCallback<CheckBoxItemView>() {
        // Id should be unique
        override fun areItemsTheSame(
            oldItem: CheckBoxItemView,
            newItem: CheckBoxItemView
        ): Boolean {
            // TODO : replace with id
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(
            oldItem: CheckBoxItemView,
            newItem: CheckBoxItemView
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {

    // view holder class
    inner class CheckBoxItemViewHolder(private val binding: ItemCheckBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(c: CheckBoxItemView) {
            with(binding) {
                college = c

                with(cbSelected) {
                    setOnClickListener {
                        isChecked = if (isChecked) {
                            onItemChecked(getItem(bindingAdapterPosition))
                            true
                        } else {
                            onItemUnChecked(getItem(bindingAdapterPosition))
                            false
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxItemViewHolder {
        val binding = ItemCheckBoxBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CheckBoxItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckBoxItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    public override fun getItem(position: Int): CheckBoxItemView {
        return currentList[position]
    }
}