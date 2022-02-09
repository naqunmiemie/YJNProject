package com.yjn.yjnproject.data.entity

data class WAndroid(
    val `data`: List<WAndroidUser>,
    val errorCode: Int,
    val errorMsg: String
){
    data class WAndroidUser(
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
    )
}

