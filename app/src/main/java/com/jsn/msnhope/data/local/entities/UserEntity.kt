package com.jsn.msnhope.data.local.entities

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val userId: String,
    val userName:String,
    val userAge: String,
    val description: String

)
