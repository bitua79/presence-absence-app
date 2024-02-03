package com.application.presence_absence.ui.features.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.databinding.FragmentExamListBinding
import com.application.presence_absence.ui.core.UiError
import com.application.presence_absence.ui.core.UiLoading
import com.application.presence_absence.ui.core.UiSuccess
import com.application.presence_absence.ui.core.UnAuthorizedError
import com.application.presence_absence.ui.features.examList.entities.ExamView
import com.application.presence_absence.ui.utils.collectOnFragment
import com.application.presence_absence.ui.utils.createSnackbar
import com.application.presence_absence.ui.utils.gone
import com.application.presence_absence.ui.utils.visible
import com.application.presence_absence.ui.widgets.AlertDialog
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

        initViewModel()
        initViews()
        initCollectors()
    }

    private fun initViewModel() {
        sharedViewModel.getAllExamList()
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
            etSearch.doAfterTextChanged {
                sharedViewModel.setExamQuery(etSearch.text?.toString()?.trim().orEmpty())
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
                    cfFilter.gone()
                    rvExamList.gone()
                    pbLoading.visible()
                }
            } else {
                with(binding) {
                    cfFilter.visible()
                    rvExamList.visible()
                    pbLoading.gone()
                }
            }

            if (it is UiError) {
                setList(emptyList())
                sharedViewModel.clearState()
                if (it is UnAuthorizedError) {
                    AlertDialog(
                        title = getString(R.string.msg_unauthorized),
                        description = getString(R.string.msg_navigate_to_login),
                        buttonText = getString(R.string.label_got_it),
                        onOkClick = {
                            findNavController().navigateUp()
                        }
                    ).show(requireFragmentManager(), "UnAuthorized")

                } else {
                    createSnackbar(it.errorStringId, binding.dividerSnackBarView).show()
                }
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
        with(binding.tvNoExam) {
            if (list.isEmpty() && sharedViewModel.uiViewState.value !is UiLoading)
                visible() else gone()
        }
    }

}