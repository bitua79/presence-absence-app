package com.application.presence_absence.ui.studentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.application.presence_absence.databinding.FragmentStudentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var listAdapter: StudentListAdapter

    private val args by navArgs<StudentListFragmentArgs>()

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
        collectResult()
    }

    private fun initViews() {
        setupAdapter()
        setupRecyclerview()

        binding.exam = args.exam
        binding.tbStudentListToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupAdapter() {
        listAdapter = StudentListAdapter(
            onPresence = {
                onPresence(it)
            },
            onAbsence = {
                onAbsence(it)
            },
            onRemoveAttendance = {
                onRemoveAttendance(it)
            }
        )
    }

    private fun onPresence(exam: StudentView) {
        //  TODO
    }

    private fun onAbsence(exam: StudentView) {
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

    private fun collectResult() {
        // TODO
        listAdapter.submitList(FakeStudentList.list)

    }

}