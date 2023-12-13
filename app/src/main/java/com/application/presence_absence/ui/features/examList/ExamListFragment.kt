package com.application.presence_absence.ui.features.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.databinding.FragmentExamListBinding
import com.application.presence_absence.ui.features.examList.entities.ExamView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExamListFragment : Fragment() {

    private lateinit var binding: FragmentExamListBinding
    private lateinit var listAdapter: ExamListAdapter

    private val sharedViewModel: ExamListViewModel by hiltNavGraphViewModels(navGraphId = R.id.navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExamListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initCollectors()
    }

    private fun initViews() {
        setupAdapter()
        setupRecyclerview()

        // init binding
        with(binding) {

            // Set values programmatically To widget.Flow work properly
            incExamPlace.title = getString(R.string.label_exam_place)
            incExamTime.title = getString(R.string.label_exam_time)
            incExamState.title = getString(R.string.label_exam_state)
        }

        setFilterTagClickListeners()
    }

    private fun setFilterTagClickListeners() {
        with(binding) {
            incExamPlace.let {
                it.root.setOnClickListener {
                    findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToChooseCollegeDialog())
                }

                // Reset exam place filter value
                it.ivClose.setOnClickListener {
                    sharedViewModel.setExamPlace(listOf())
                }
            }

            incExamTime.let {
                it.root.setOnClickListener {
                    // TODO: update after implement
                    Toast.makeText(requireContext(), "به زودی...", Toast.LENGTH_SHORT).show()
                }

                // Reset exam time filter value
                it.ivClose.setOnClickListener {
                    sharedViewModel.setExamTime(null)
                }
            }


            incExamState.let {
                it.root.setOnClickListener {
                    findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToExamStateListBottomSheet())
                }

                // Reset exam state filter value
                it.ivClose.setOnClickListener {
                    sharedViewModel.setExamState(listOf())
                }
            }
        }
    }

    private fun setupAdapter() {
        listAdapter = ExamListAdapter(
            onItemClicked = {
                onItemClicked(it)
            }
        )
    }

    private fun setupRecyclerview() {
        with(binding.rvExamList) {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun onItemClicked(exam: ExamView) {
        findNavController().navigate(
            ExamListFragmentDirections.actionExamListFragmentToStudentListFragment(
                exam
            )
        )
    }

    private fun initCollectors() {
        // Update filter state
        lifecycleScope.launch {
            sharedViewModel.filter.collect { examFilter ->
                binding.filterState = examFilter

                // Update list
                val college = examFilter.examPlace
                val time = examFilter.examTime
                val state = examFilter.examState

                var filteredList = FakeExamList.list

                if (college.isNotEmpty()) {
                    filteredList = filteredList.filter {
                        college.contains(it.collegeName)
                    }
                }

                // TODO: update after implement
                if (!time.isNullOrEmpty()) {
                }

                if (state.isNotEmpty()) {
                    filteredList = filteredList.filter {
                        state.contains(getString(it.state.titleId))
                    }
                }

                setList(filteredList)
            }
        }

        // TODO: Replace static data with repo response
        setList(FakeExamList.list)
    }

    private fun setList(list: List<ExamView>) {
        listAdapter.submitList(list)
        binding.tvNoExam.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

}