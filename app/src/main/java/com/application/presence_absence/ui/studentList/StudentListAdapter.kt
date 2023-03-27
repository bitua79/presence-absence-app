package com.application.presence_absence.ui.studentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.presence_absence.databinding.ItemStudentBinding

class StudentListAdapter(
    private val onPresence: (student: StudentView) -> Unit,
    private val onAbsence: (student: StudentView) -> Unit,
    private val onRemoveAttendance: (student: StudentView) -> Unit,

    ) : ListAdapter<StudentView, StudentListAdapter.StudentViewHolder>(

    object : DiffUtil.ItemCallback<StudentView>() {
        // Id should be unique
        override fun areItemsTheSame(
            oldItem: StudentView,
            newItem: StudentView
        ): Boolean {
            // TODO : replace with id
            return oldItem.name == newItem.name
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

        fun bind(e: StudentView) {
            with(binding) {
                student = e
                isAttendanceSet = e.presence != Attendance.NoSet
                isPresent = e.presence == Attendance.Presence

                ivPresence.setOnClickListener {
                    updateItem(e, Attendance.Presence)
                    onPresence(e)
                }

                ivAbsence.setOnClickListener {
                    updateItem(e, Attendance.Absence)
                    onAbsence(e)
                }

                ivEraser.setOnClickListener {
                    updateItem(e, Attendance.NoSet)
                    onRemoveAttendance(e)
                }
            }
        }

        private fun updateItem(e: StudentView, attendance: Attendance) {
            e.setAttendance(attendance = attendance)

            with(binding) {
                student = e
                isAttendanceSet = e.presence != Attendance.NoSet
                isPresent = e.presence == Attendance.Presence
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