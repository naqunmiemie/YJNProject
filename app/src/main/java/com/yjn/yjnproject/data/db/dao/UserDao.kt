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

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun findByTop(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)
}