package com.jsn.msnhope.data.repository

import com.jsn.msnhope.data.remote.model.Product
import com.jsn.msnhope.data.remote.model.api.ApiInterface
import com.jsn.msnhope.data.remote.utils.Outcome
import javax.inject.Inject


class ProductsRepository @Inject constructor(private val apiService: ApiInterface) {


//    //local data
//    fun fetchUsers(): LiveData<List<UserEntity>> = userDao.fetchData()
//    fun fetchUsersById(userId: String): LiveData<UserEntity> = userDao.fetchDataById(userId)
//    suspend fun insertUser(userEntity: UserEntity) = userDao.insert(userEntity)
//    suspend fun updateUser(userEntity: UserEntity) = userDao.update(userEntity)
//    suspend fun deleteUser(userEntity: UserEntity) = userDao.delete(userEntity)

    //remote data
    suspend fun fetchProducts(): Outcome<List<Product>?> {
        return try {
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    Outcome.Success(response.body())
                } ?: Outcome.Error(errorCode = 500, errorMessage = "Empty Data")

            } else {
                Outcome.Error(response.code(), response.message())
            }
        } catch (e: Exception) {
            Outcome.Error(500, e.localizedMessage ?: "Unknown Error")
        }

    }

}