package com.yjn.yjnproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    val avatar_url: String,
    val created_at: String,
    val followers: Int,
    val following: Int,
    val html_url: String,
    val login: String,
    val public_gists: Int,
    val public_repos: Int,
    val type: String,
    val updated_at: String,
    val url: String
)