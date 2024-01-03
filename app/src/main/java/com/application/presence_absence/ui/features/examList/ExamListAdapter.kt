package com.application.presence_absence.ui.features.examList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemExamBinding
import com.application.presence_absence.ui.features.examList.entities.ExamStatusTagView
import com.application.presence_absence.ui.features.examList.entities.ExamView

class ExamListAdapter(
    private val onItemClicked: (exam: ExamView) -> Unit

) : ListAdapter<ExamView, ExamListAdapter.ExamViewHolder>(

    object : DiffUtil.ItemCallback<ExamView>() {
        // Id should be unique
        override fun areItemsTheSame(
            oldItem: ExamView,
            newItem: ExamView
        ): Boolean {
            return oldItem.id == newItem.id
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
                tag = ExamStatusTagView.buildTagChip(e.status)
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