package com.application.presence_absence.ui.examList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.databinding.FragmentExamListBinding

class ExamListFragment : Fragment() {

    private lateinit var binding: FragmentExamListBinding
    private lateinit var listAdapter: ExamListAdapter

    private var list: List<ExamView> = mutableListOf()

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

        binding.tbExamListToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnDoFilter.setOnClickListener {
            doFilter()
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

        listAdapter.submitList(FakeExamList.list)
        list = FakeExamList.list
    }

    private fun doFilter() {
        with(binding) {
            val college = etCollege.text
            val day = etDay.text
            val hour = etHour.text

            var filteredList = list

            if (!college.isNullOrEmpty()){
                filteredList = filteredList.filter {
                    it.collegeName == college.toString()
                }
            }

            if (!day.isNullOrEmpty()){
                filteredList = filteredList.filter {
                    it.day == day.toString()
                }
            }

            if (!hour.isNullOrEmpty()) {
                filteredList = filteredList.filter {
                    it.hour == hour.toString()
                }
            }

            listAdapter.submitList(filteredList)
        }
    }

    private fun onItemClicked(exam: ExamView) {
        // TODO: Implement navigation to student list
    }

    private fun collectResult() {
        // TODO: Replace static data with repo response
        listAdapter.submitList(FakeExamList.list)
    }

}