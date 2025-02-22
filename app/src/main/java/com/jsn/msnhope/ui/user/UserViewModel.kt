package com.jsn.msnhope.ui.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jsn.msnhope.data.local.database.AppDatabase
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.data.repository.AppDataSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppDataSourceRepository

    private var _users:LiveData<List<UserEntity>> = MutableLiveData()
    val users: LiveData<List<UserEntity>> get() = _users
    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = AppDataSourceRepository(userDao)
        fetchUser()
    }

    private fun fetchUser() {
       _users =  repository.fetchUsers()
    }

    fun insertUser(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(userEntity)
    }


    fun updateUser(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(userEntity)
    }

    fun deleteUser(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteUser(userEntity)
    }



}