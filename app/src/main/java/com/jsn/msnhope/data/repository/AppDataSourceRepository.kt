package com.jsn.msnhope.data.repository

import androidx.lifecycle.LiveData
import com.jsn.msnhope.data.local.dao.UserDao
import com.jsn.msnhope.data.local.database.AppDatabase
import com.jsn.msnhope.data.local.entities.UserEntity

class AppDataSourceRepository(private val userDao: UserDao) {


    //local data
    fun fetchUsers(): LiveData<List<UserEntity>> = userDao.fetchData()
    fun fetchUsersById(userId: String): LiveData<UserEntity> = userDao.fetchDataById(userId)
    suspend fun insertUser(userEntity: UserEntity) = userDao.insert(userEntity)
    suspend fun updateUser(userEntity: UserEntity) = userDao.update(userEntity)
    suspend fun deleteUser(userEntity: UserEntity) = userDao.delete(userEntity)

}