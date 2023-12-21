package com.application.presence_absence.ui.features.examList

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.application.presence_absence.R
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.findByTitle
import com.application.presence_absence.ui.widgets.CheckBoxItemView
import com.application.presence_absence.ui.widgets.CheckBoxListBottomSheet

class ExamDayListBottomSheet : CheckBoxListBottomSheet(R.string.label_select_exam_day) {

    private val sharedViewModel: ExamListViewModel by hiltNavGraphViewModels(navGraphId = R.id.navigation)

    override fun initFilteredOptions() {
        listAdapter.currentList.forEach { item ->
            item.checked = sharedViewModel.filter.value.examDay.map { it.title }.contains(item.text)
        }
    }

    override fun initValues() {
        // Convert ExamTime items to checkBocItemViews to insert in listAdapter<CheckBocItemViews>
        val list = ExamDay.values().map { CheckBoxItemView(it.title, false) }
        listAdapter.submitList(list)
    }

    override fun onItemChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examDay
        val titleList = list.map { it.title }
        val index = titleList.indexOf(c.text)
        if (index == -1) {
            val l = list.toMutableList()
            findByTitle(c.text)?.let {
                l.add(it)
                sharedViewModel.setExamDay(l)
            }
        }
    }

    override fun onItemUnChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examDay
        val titleList = list.map { it.title }
        val index = titleList.indexOf(c.text)
        if (index != -1) {
            val l = list.toMutableList()
            findByTitle(c.text)?.let {
                l.remove(it)
                sharedViewModel.setExamDay(l)
            }
        }
    }
}