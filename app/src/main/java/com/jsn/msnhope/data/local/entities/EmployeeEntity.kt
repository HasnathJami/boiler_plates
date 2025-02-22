package com.jsn.msnhope.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    val _empId: Int,
)
