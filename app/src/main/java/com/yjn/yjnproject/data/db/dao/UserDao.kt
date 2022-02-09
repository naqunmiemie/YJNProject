package com.yjn.yjnproject.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjn.yjnproject.data.entity.GithubUser

@Dao
interface UserDao {
    @Query("SELECT * FROM GithubUser")
    fun getAll(): List<GithubUser>

    @Query("SELECT * FROM GithubUser WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<GithubUser>

    @Insert
    fun insertAll(vararg githubUsers: GithubUser)

    @Delete
    fun delete(githubUser: GithubUser)

    @Query("SELECT * FROM GithubUser LIMIT 1")
    fun findByTop(): LiveData<GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(githubUser: GithubUser)
}