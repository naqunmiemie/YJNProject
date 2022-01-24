package com.yjn.yjnproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yjn.yjnproject.App
import com.yjn.yjnproject.data.db.dao.UserDao
import com.yjn.yjnproject.data.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    companion object{
        private const val databaseName = "YJNProject_db"
        private var databaseInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    App.getInstance(),
                    AppDatabase::class.java, databaseName
                ).build()

//                val db = Room.databaseBuilder(
//                    applicationContext,
//                    AppDatabase::class.java, "database-name"
//                ).build()
            }
            return databaseInstance as AppDatabase
        }
    }

    abstract fun userDao() : UserDao
}