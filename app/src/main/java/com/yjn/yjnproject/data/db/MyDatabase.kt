package com.yjn.yjnproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yjn.yjnproject.data.db.dao.UserDao
import com.yjn.yjnproject.data.model.User

@Database(entities = [User::class],version = 1)
abstract class MyDatabase : RoomDatabase(){
    companion object{
        private const val databaseName = "YJNProject_db"
        private var databaseInstance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java, databaseName
                ).build()
            }
            return databaseInstance as MyDatabase
        }
    }

    abstract fun userDao() : UserDao
}