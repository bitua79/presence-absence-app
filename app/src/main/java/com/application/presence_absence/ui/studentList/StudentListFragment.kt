package com.application.presence_absence.ui.studentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.application.presence_absence.R
import com.application.presence_absence.databinding.FragmentStudentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var listAdapter: StudentListAdapter

    private val args by navArgs<StudentListFragmentArgs>()

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
                }

                // Reset exam place filter value
                it.ivClose.setOnClickListener {
                    viewModel.setShowPresents(null)
                }
            }

            incShowAbsents.let {
                it.root.setOnClickListener {
                    viewModel.setShowAbsents(true)
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

    private fun onStudentSetPresence(exam: StudentView) {

    }

    private fun onStudentSetAbsence(exam: StudentView) {
        //  TODO
    }

    private fun onRemoveAttendance(exam: StudentView) {
        //  TODO
    }

    private fun setupRecyclerview() {
        with(binding.rvStudentList) {
            setHasFixedSize(true)
            adapter = listAdapter
        }

        listAdapter.submitList(FakeStudentList.list)
    }

    private fun initControllers() {
        lifecycleScope.launch {
            viewModel.filter.collect { stdFilter ->
                binding.filterState = stdFilter

                // Update list
                val presence = stdFilter.showPresents
                val absence = stdFilter.showAbsents


                var filteredList = FakeStudentList.list

                if (presence == true) {
                    filteredList = filteredList.filter {
                        it.attendance == StudentAttendance.PRESENCE
                    }
                }

                if (absence == true) {
                    filteredList = filteredList.filter {
                        it.attendance == StudentAttendance.ABSENCE
                    }
                }

                setList(filteredList)
            }
        }

        // TODO: Replace static data with repo response
        setList(FakeStudentList.list)

    }

    private fun setList(list: List<StudentView>) {
        listAdapter.submitList(list)
        binding.tvNoStudent.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }
}