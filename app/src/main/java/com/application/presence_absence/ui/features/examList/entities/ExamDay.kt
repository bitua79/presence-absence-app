package com.application.presence_absence.ui.features.examList.entities

enum class ExamDay(val n_th: Int, val title: String) {
    DAY_0(0, "روز صفرم"),
    DAY_1(1, "روز اول"),
    DAY_2(2, "روز دوم"),
    DAY_3(3, "روز سوم"),
    DAY_4(4, "روز چهارم"),
    DAY_5(5, "روز پنجم"),
    DAY_6(6, "روز ششم"),
    DAY_7(7, "روز هفتم"),
    DAY_8(8, "روز هشتم"),
    DAY_9(9, "روز نهم"),
    DAY_10(10, "روز دهم"),
    DAY_11(11, "روز یازدهم"),
    DAY_12(12, "روز دوازدهم"),
    DAY_13(13, "روز سیزدهم"),
    DAY_14(14, "روز چهاردهم"),
    DAY_15(15, "روز پانزدهم"),
    DAY_16(16, "روز شانزدهم");
}

fun findExamDayByTitle(title: String): ExamDay? {
    ExamDay.values().forEach {
        if (title == it.title) {
            return it
        }
    }
    return null
}

fun findExamDayByNth(n_th: Int): ExamDay? {
    ExamDay.values().forEach {
        if (n_th == it.n_th) {
            return it
        }
    }
    return null
}