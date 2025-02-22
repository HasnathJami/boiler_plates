package com.jsn.msnhope.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jsn.msnhope.data.local.entities.UserEntity

@Dao
interface  UserDao{
    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun fetchData():LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE userId = :id ")
     fun fetchDataById(id: String):LiveData<UserEntity>
}
