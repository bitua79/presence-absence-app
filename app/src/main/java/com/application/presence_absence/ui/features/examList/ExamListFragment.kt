package com.application.presence_absence.ui.features.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            incExamDay.title = getString(R.string.label_exam_day)
            incExamState.title = getString(R.string.label_exam_state)
        }

        setFilterTagClickListeners()
    }

    private fun setFilterTagClickListeners() {
        with(binding) {
            incExamPlace.let {
                it.root.setOnClickListener {
                    findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToCollegeListBottomSheet())
                }

                // Reset exam place filter value
                it.ivClose.setOnClickListener {
                    sharedViewModel.setExamPlace(listOf())
                }
            }

            incExamDay.let {
                it.root.setOnClickListener {
                    findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToExamDayListBottomSheet())
                }

                // Reset exam time filter value
                it.ivClose.setOnClickListener {
                    sharedViewModel.setExamDay(listOf())
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
                val day = examFilter.examDay.map { it.n_th }
                val state = examFilter.examState

                var filteredList = FakeExamList.list

                if (college.isNotEmpty()) {
                    filteredList = filteredList.filter {
                        college.contains(it.collegeName)
                    }
                }

                if (day.isNotEmpty()) {
                    filteredList = filteredList.filter {
                        day.contains(it.day)
                    }
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