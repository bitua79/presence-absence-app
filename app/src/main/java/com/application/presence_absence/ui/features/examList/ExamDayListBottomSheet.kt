package com.application.presence_absence.ui.features.examList

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.application.presence_absence.R
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.findExamDayByTitle
import com.application.presence_absence.ui.widgets.CheckBoxItemView
import com.application.presence_absence.ui.widgets.CheckBoxListBottomSheet

class ExamDayListBottomSheet : CheckBoxListBottomSheet(R.string.label_select_exam_day, true) {

    // Convert ExamTime items to checkBocItemViews to insert in listAdapter<CheckBocItemViews>
    override var itemList: List<CheckBoxItemView> =
        ExamDay.values().map { CheckBoxItemView(it.title, false) }

    private val sharedViewModel: ExamListViewModel by hiltNavGraphViewModels(navGraphId = R.id.navigation)

    override fun initFilteredOptions() {
        listAdapter.currentList.forEach { item ->
            item.checked = sharedViewModel.filter.value.examDay.map { it.title }.contains(item.text)
        }
    }

    override fun onItemChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examDay
        val titleList = list.map { it.title }
        val index = titleList.indexOf(c.text)
        if (index == -1) {
            val l = list.toMutableList()
            findExamDayByTitle(c.text)?.let {
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
            findExamDayByTitle(c.text)?.let {
                l.remove(it)
                sharedViewModel.setExamDay(l)
            }
        }
    }
}