package com.application.presence_absence.ui.features.examList

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.application.presence_absence.R
import com.application.presence_absence.ui.features.examList.entities.ExamStatus
import com.application.presence_absence.ui.widgets.CheckBoxItemView
import com.application.presence_absence.ui.widgets.CheckBoxListBottomSheet

class ExamStatusListBottomSheet : CheckBoxListBottomSheet(title = R.string.label_select_exam_state) {

    private val sharedViewModel: ExamListViewModel by hiltNavGraphViewModels(navGraphId = R.id.navigation)

    override fun initFilteredOptions() {
        listAdapter.currentList.forEach {
            it.checked = sharedViewModel.filter.value.examState.contains(it.text)
        }
    }

    override fun initValues() {
        val list = ExamStatus.values().map {
            CheckBoxItemView(getString(it.titleId), checked = false)
        }
        listAdapter.submitList(list)
    }

    override fun onItemChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examState
        val index = list.indexOf(c.text)
        if (index == -1) {
            val l = list.toMutableList()
            l.add(c.text)
            sharedViewModel.setExamState(l)

        }
    }

    override fun onItemUnChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examState
        val index = list.indexOf(c.text)
        if (index != -1) {
            val l = list.toMutableList()
            l.remove(c.text)
            sharedViewModel.setExamState(l)
        }
    }
}