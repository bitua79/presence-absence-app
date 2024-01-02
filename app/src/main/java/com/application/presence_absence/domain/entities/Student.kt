package com.application.presence_absence.domain.entities

import com.application.presence_absence.core.utils.UrlHelper.localHostToEmulatorLocalHost
import com.application.presence_absence.data.entities.StudentEntity
import com.application.presence_absence.ui.features.studentList.entities.StudentStatus
import com.application.presence_absence.ui.features.studentList.entities.StudentView
import com.application.presence_absence.ui.features.studentList.entities.findStudentStatusByNumValue

data class Student(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val national_code: String,
    val profile: String,
    val status: Int,
    val student_id: String
) {
    fun toStudentView() = StudentView(
        id = id,
        name = first_name,
        profileUrl = profile.localHostToEmulatorLocalHost(),
        idNumber = national_code,
        status = findStudentStatusByNumValue(status) ?: StudentStatus.NOT_SET
    )
}

fun StudentEntity.toStudent() = Student(
    first_name = first_name,
    id = id,
    last_name = last_name,
    national_code = national_code,
    profile = profile,
    status = status,
    student_id = student_id
)