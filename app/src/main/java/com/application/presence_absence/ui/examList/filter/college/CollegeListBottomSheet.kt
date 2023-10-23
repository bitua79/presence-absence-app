package com.application.presence_absence.ui.examList.filter.college

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.application.presence_absence.R
import com.application.presence_absence.ui.core.CheckBoxItemView
import com.application.presence_absence.ui.core.CheckBoxListBottomSheet
import com.application.presence_absence.ui.examList.filter.FilterViewModel

class CollegeListBottomSheet : CheckBoxListBottomSheet() {

    private val sharedViewModel: FilterViewModel by hiltNavGraphViewModels(navGraphId = R.id.navigation)

    override fun initFilteredOptions() {
        listAdapter.currentList.forEach {
            it.checked = sharedViewModel.filter.value.examPlace.contains(it.text)
        }
    }

    override fun initValues() {
        val list = FakeCollegeList.list
        listAdapter.submitList(list)
    }

    override fun onItemChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examPlace
        val index = list.indexOf(c.text)
        if (index == -1) {
            val l = list.toMutableList()
            l.add(c.text)
            sharedViewModel.setExamPlace(l)
        }
    }

    override fun onItemUnChecked(c: CheckBoxItemView) {
        val list = sharedViewModel.filter.value.examPlace
        val index = list.indexOf(c.text)
        if (index != -1) {
            val l = list.toMutableList()
            l.remove(c.text)
            sharedViewModel.setExamPlace(l)
        }
    }
}