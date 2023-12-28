package com.application.presence_absence.domain.entities

import com.application.presence_absence.data.entities.ExamEntity
import com.application.presence_absence.ui.features.examList.entities.ExamDay
import com.application.presence_absence.ui.features.examList.entities.ExamStatus
import com.application.presence_absence.ui.features.examList.entities.ExamView
import com.application.presence_absence.ui.features.examList.entities.findExamDayByNth
import com.application.presence_absence.ui.utils.getShDate
import saman.zamani.persiandate.PersianDate
import java.util.concurrent.TimeUnit

data class Exam(
    val id: Int,
    val title: String,
    val class_name: String,
    val faculty: String,
    val nth_day: Int,
    val date: Long,
    val time: Int,
    val status: Int
) {
    fun toExamView() = ExamView(
        id = id,
        name = title,
        className = class_name,
        faculty = faculty,
        day = findExamDayByNth(nth_day) ?: ExamDay.DAY_0,
        dateView = PersianDate(date).getShDate(),
        hour = time,
        state = getStatue()
    )

    private fun getStatue(): ExamStatus {
        val current = System.currentTimeMillis()
        val examTime = date + TimeUnit.HOURS.toMillis(time.toLong())

        return if (status == -1) {
            ExamStatus.CANCELLED
        } else if (current < examTime) {
            ExamStatus.NOT_STARTED
        } else if (current - examTime <= TimeUnit.HOURS.toMillis(2)) {
            ExamStatus.IN_PROGRESS
        } else {
            ExamStatus.FINISHED
        }
    }
}


fun ExamEntity.toExam() = Exam(
    id = id,
    title = title,
    class_name = class_name,
    faculty = faculty,
    nth_day = nth_day,
    date = date,
    time = time,
    status = status
)
