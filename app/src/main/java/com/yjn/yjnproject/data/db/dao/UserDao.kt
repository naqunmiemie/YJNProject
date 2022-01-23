package com.yjn.yjnproject.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjn.yjnproject.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun findByTop(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)
}