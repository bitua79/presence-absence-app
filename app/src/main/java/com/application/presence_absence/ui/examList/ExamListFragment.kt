package com.application.presence_absence.ui.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.databinding.FragmentExamListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExamListFragment : Fragment() {

    private lateinit var binding: FragmentExamListBinding
    private lateinit var listAdapter: ExamListAdapter

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
        collectResult()
    }

    private fun initViews() {
        setupAdapter()
        setupRecyclerview()

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
        findNavController().navigate(ExamListFragmentDirections.actionExamListFragmentToStudentListFragment(exam))
    }

    private fun collectResult() {
        // TODO: Replace static data with repo response
        listAdapter.submitList(FakeExamList.list)
    }

}