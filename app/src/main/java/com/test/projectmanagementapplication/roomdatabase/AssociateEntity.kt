package com.test.projectmanagementapplication.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

/*-----------CREATED Associate TABLE HERE ------------*/
@Entity(tableName = "associate")
data class AssociateEntity(
    @PrimaryKey
    val empID: String,
    val empName: String?,
    val empBand: String?,
    val empDesignation: String?,
    val empDepartment: String?,
    val empProject: String?,
)