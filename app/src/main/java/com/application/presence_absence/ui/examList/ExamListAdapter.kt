package com.application.presence_absence.ui.examList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemExamBinding

class ExamListAdapter(
    private val onItemClicked: (exam: ExamView) -> Unit

) : ListAdapter<ExamView, ExamListAdapter.ExamViewHolder>(

    object : DiffUtil.ItemCallback<ExamView>() {
        // Id should be unique
        override fun areItemsTheSame(
            oldItem: ExamView,
            newItem: ExamView
        ): Boolean {
            // TODO : replace with id
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ExamView,
            newItem: ExamView
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {

    // view holder class
    inner class ExamViewHolder(private val binding: ItemExamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClicked(getItem(bindingAdapterPosition))
            }
        }

        fun bind(e: ExamView) {
            with(binding) {
                exam = e
                tag = ExamTagView.buildTagChip(e.state)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val binding = ItemExamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    public override fun getItem(position: Int): ExamView {
        return currentList[position]
    }
}