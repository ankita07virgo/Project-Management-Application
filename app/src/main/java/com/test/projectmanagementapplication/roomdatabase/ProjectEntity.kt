package com.test.projectmanagementapplication.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

/*-----------CREATED Project TABLE HERE ------------*/
@Entity(tableName = "project")
data class ProjectEntity(
    @PrimaryKey
    val projectname: String

)