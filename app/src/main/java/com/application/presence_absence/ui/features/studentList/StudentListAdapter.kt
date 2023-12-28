package com.application.presence_absence.ui.features.studentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemStudentBinding
import com.application.presence_absence.ui.features.studentList.entities.StudentStatus
import com.application.presence_absence.ui.features.studentList.entities.StudentAttendanceTagView
import com.application.presence_absence.ui.features.studentList.entities.StudentView

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
                tag = StudentAttendanceTagView.buildTagChip(std.status)
                clPresent.setOnClickListener {
                    onSetPresence(std)
                    val status = StudentStatus.PRESENCE
                    tag = StudentAttendanceTagView.buildTagChip(status)
                    val newS = student
                    newS?.setAttendance(status)
                    student = newS ?: null
                    incAttendanceTag.tag = tag
                }

                clAbsence.setOnClickListener {
                    onSetAbsence(std)
                    val status = StudentStatus.ABSENCE
                    tag = StudentAttendanceTagView.buildTagChip(status)
                    val newS = student ?: null
                    newS?.setAttendance(status)
                    student = newS
                    incAttendanceTag.tag = tag
                }

                incAttendanceTag.ivEdit.setOnClickListener {
                    onRemoveAttendance(std)
                    val status = StudentStatus.NOT_SET
                    tag = StudentAttendanceTagView.buildTagChip(status)
                    val newS = student ?: null
                    newS?.setAttendance(status)
                    student = newS
                    incAttendanceTag.tag = tag
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