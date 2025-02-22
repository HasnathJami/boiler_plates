package com.jsn.msnhope.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jsn.msnhope.data.local.dao.UserDao
import com.jsn.msnhope.data.local.entities.EmployeeEntity
import com.jsn.msnhope.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, EmployeeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}