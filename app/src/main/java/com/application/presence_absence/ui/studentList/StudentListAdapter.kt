package com.application.presence_absence.ui.studentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemStudentBinding

class StudentListAdapter(
    private val onSetPresence: (student: StudentView) -> Unit,
    private val onSetAbsence: (student: StudentView) -> Unit,
    private val onRemoveAttendance: (student: StudentView) -> Unit
) : ListAdapter<StudentView, StudentListAdapter.StudentViewHolder>(

    object : DiffUtil.ItemCallback<StudentView>() {
        // Id should be unique
        override fun areItemsTheSame(
            oldItem: StudentView,
            newItem: StudentView
        ): Boolean {
            return oldItem.idNumber == newItem.idNumber
        }

        override fun areContentsTheSame(
            oldItem: StudentView,
            newItem: StudentView
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {

    // view holder class
    inner class StudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(std: StudentView) {
            with(binding) {
                student = std
                tag = StudentAttendanceTagView.buildTagChip(std.attendance)
                clPresent.setOnClickListener {
                    //TODO: update attendance
                    onSetPresence(std)
                }

                clAbsence.setOnClickListener {
                    //TODO: update attendance
                    onSetAbsence(std)
                }

                incAttendanceTag.root.setOnClickListener {
                    //TODO: update attendance
                    onRemoveAttendance(std)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    public override fun getItem(position: Int): StudentView {
        return currentList[position]
    }
}