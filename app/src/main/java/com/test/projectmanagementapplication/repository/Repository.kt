package com.test.projectmanagementapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.test.projectmanagementapplication.roomdatabase.AppDataBase
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import kotlinx.coroutines.Dispatchers

/*----------- REPOSITORY DATASOURCE------------*/
class Repository  constructor(private var appdataBase: AppDataBase) {
    suspend fun AddAssociate(associateEntity: AssociateEntity){
        appdataBase.appdao().insert(associateEntity)

    }

    suspend fun AddProject(projectEntity: ProjectEntity){
        appdataBase.appdao().insert(projectEntity)

    }

    suspend fun getAllAssociate(): List<AssociateEntity> = appdataBase.appdao().getAllAssociate()

    suspend fun getAllProject(): List<ProjectEntity> = appdataBase.appdao().getAllProject()

    suspend fun deleteAssociate(associateEntity: AssociateEntity)= appdataBase.appdao().delete(associateEntity)
}