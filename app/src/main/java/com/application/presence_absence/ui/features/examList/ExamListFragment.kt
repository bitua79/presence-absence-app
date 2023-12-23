package com.application.presence_absence.ui.features.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.core.extensions.collectOnFragment
import com.application.presence_absence.databinding.FragmentExamListBinding
import com.application.presence_absence.ui.features.examList.entities.ExamView
import com.application.presence_absence.ui.widgets.UiError
import com.application.presence_absence.ui.widgets.UiLoading
import com.application.presence_absence.ui.widgets.UiSuccess
import dagger.hilt.android.AndroidEntryPoint

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
                    findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToFacultyListBottomSheet())
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

        sharedViewModel.uiViewState.collectOnFragment(this) {
            if (it is UiLoading) {
                with(binding) {
                    cfFilter.visibility = View.GONE
                    rvExamList.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                }
            } else {
                with(binding) {
                    cfFilter.visibility = View.VISIBLE
                    rvExamList.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                }
            }

            if (it is UiError) {
                setList(emptyList())
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                sharedViewModel.clearState()
            } else if (it is UiSuccess) {
                sharedViewModel.clearState()
            }
        }

        // Update filter state
        sharedViewModel.filter.collectOnFragment(this) { examFilter ->
            binding.filterState = examFilter
        }

        sharedViewModel.dataViewState.collectOnFragment(this) {
            setList(it.filteredList.orEmpty())
        }
    }

    private fun setList(list: List<ExamView>) {
        listAdapter.submitList(list)
        binding.tvNoExam.visibility =
            if (list.isEmpty() && sharedViewModel.uiViewState.value !is UiLoading) View.VISIBLE else View.GONE
    }

}