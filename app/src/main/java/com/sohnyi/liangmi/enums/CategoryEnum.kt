package com.sohnyi.liangmi.enums

/**
 * 分类枚举类
 */
enum class CategoryEnum(val id: Int, val title: String) {
    SOCIAL(0, "social"),
    FINANCE(1, "finance"),
    GAME(2, "game"),
    EDUCATION(3, "education"),
    ENTERTAINMENT(4, "entertainment"),
    LIFESTYLE(5, "lifestyle"),
    WORK_OR_STUDY(6, "work_or_study"),
    PRODUCTIVITY(7, "productivity"),
    UTILITIES(8, "utilities"),
    OTHER(9, "other")
}