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
    OTHER(6, "other")
}