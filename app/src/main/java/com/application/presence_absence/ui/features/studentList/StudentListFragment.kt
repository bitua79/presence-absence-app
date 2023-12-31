package com.application.presence_absence.ui.features.studentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.application.presence_absence.R
import com.application.presence_absence.databinding.FragmentStudentListBinding
import com.application.presence_absence.ui.features.examList.entities.ExamView
import com.application.presence_absence.ui.features.studentList.entities.StudentStatus
import com.application.presence_absence.ui.features.studentList.entities.StudentView
import com.application.presence_absence.ui.utils.collectOnFragment
import com.application.presence_absence.ui.utils.gone
import com.application.presence_absence.ui.utils.visible
import com.application.presence_absence.ui.widgets.UiError
import com.application.presence_absence.ui.widgets.UiLoading
import com.application.presence_absence.ui.widgets.UiSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var listAdapter: StudentListAdapter

    private val args by navArgs<StudentListFragmentArgs>()
    lateinit var exam: ExamView

    private val viewModel: StudentListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initControllers()

        exam = args.exam
        viewModel.getAllStudents(exam.id.toString())
    }

    private fun initViews() {
        setupAdapter()
        setupRecyclerview()

        with(binding) {
            exam = args.exam
            incShowPresents.title = getString(R.string.label_show_presents)
            incShowAbsents.title = getString(R.string.label_show_absents)

            incShowPresents.let {
                it.root.setOnClickListener {
                    viewModel.setShowPresents(true)
                    viewModel.setShowAbsents(null)
                }

                // Reset exam place filter value
                it.ivClose.setOnClickListener {
                    viewModel.setShowPresents(null)
                }
            }

            incShowAbsents.let {
                it.root.setOnClickListener {
                    viewModel.setShowAbsents(true)
                    viewModel.setShowPresents(null)
                }

                // Reset exam place filter value
                it.ivClose.setOnClickListener {
                    viewModel.setShowAbsents(null)
                }
            }

            tbStudentListToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupAdapter() {
        listAdapter = StudentListAdapter(
            onSetPresence = {
                onStudentSetPresence(it)
            },
            onSetAbsence = {
                onStudentSetAbsence(it)
            },
            onRemoveAttendance = {
                onRemoveAttendance(it)
            }
        )
    }

    private fun onStudentSetPresence(student: StudentView) {
        viewModel.setStudentAttendanceStatus(
            examId = exam.id.toString(),
            studentId = student.id.toString(),
            state = StudentStatus.PRESENCE
        )
    }

    private fun onStudentSetAbsence(student: StudentView) {
        viewModel.setStudentAttendanceStatus(
            examId = exam.id.toString(),
            studentId = student.id.toString(),
            state = StudentStatus.ABSENCE
        )
    }

    private fun onRemoveAttendance(student: StudentView) {
        viewModel.setStudentAttendanceStatus(
            examId = exam.id.toString(),
            studentId = student.id.toString(),
            state = StudentStatus.NOT_SET
        )
    }

    private fun setupRecyclerview() {
        with(binding.rvStudentList) {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun initControllers() {
        viewModel.uiViewState.collectOnFragment(this) {
            if (it is UiLoading) {
                with(binding) {
                    tvFilter.gone()
                    incShowPresents.root.gone()
                    incShowAbsents.root.gone()
                    rvStudentList.gone()
                    pbLoading.visible()
                }
            } else {
                with(binding) {
                    tvFilter.visible()
                    incShowPresents.root.visible()
                    incShowAbsents.root.visible()
                    rvStudentList.visible()
                    pbLoading.gone()
                }
            }

            if (it is UiError) {
                setList(emptyList())
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                viewModel.clearState()
            } else if (it is UiSuccess) {
                viewModel.clearState()
            }
        }

        viewModel.dataViewState.collectOnFragment(this) {
            setList(it.filteredList.orEmpty())
        }

        lifecycleScope.launch {
            viewModel.filter.collect { stdFilter ->
                binding.filterState = stdFilter
            }
        }
    }

    private fun setList(list: List<StudentView>) {
        listAdapter.submitList(list)
        binding.tvNoStudent.visibility =
            if (viewModel.uiViewState.value !is UiLoading && list.isEmpty()) View.VISIBLE else View.GONE
    }
}