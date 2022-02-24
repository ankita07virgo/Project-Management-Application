package com.test.projectmanagementapplication.roomdatabase

import androidx.room.*

@Dao
interface AssociateDao {
    /*-----------INSERT Associate ITEM IN TABLE------------*/
    @Insert
    fun insert(associate: AssociateEntity)

    @Query("SELECT * FROM associate")
    suspend fun getAllAssociate(): List<AssociateEntity>


    @Insert
    fun insert(project: ProjectEntity)

    @Query("SELECT * FROM project")
    suspend fun getAllProject(): List<ProjectEntity>

    @Delete
    fun delete(associate: AssociateEntity)

}