package com.test.projectmanagementapplication.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.test.projectmanagementapplication.roomdatabase.Resource
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.repository.Repository
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssociateViewModel(private val repository: Repository) : ViewModel() {
    fun addEmployee(associateEntity: AssociateEntity, context: Context) = liveData(Dispatchers.IO) {

        if (associateEntity.empID.isEmpty()) {
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.emptyID)
                )
            )
        } else if (associateEntity.empName?.isEmpty() == true)
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.emptyName)
                )
            )
        else if (associateEntity.empBand?.isEmpty() == true)
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.emptyBand)
                )
            )
        else if (associateEntity.empDesignation?.isEmpty() == true)
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.emptyDesignation)
                )
            )
        else if (associateEntity.empDepartment?.isEmpty() == true)
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.selectDepartment)
                )
            )
        else if (associateEntity.empProject?.isEmpty() == true)
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.selectProject)
                )
            )
        else
            emit(
                Resource.success(
                    message = context.resources.getString(R.string.successfully),
                    data = repository.AddAssociate(associateEntity)
                )
            )

    }



    fun fetchAssociateList(context: Context) : LiveData<Resource<List<AssociateEntity>>> {
        return  liveData(Dispatchers.IO) {

            try {
                emit(Resource.success(context.resources.getString(R.string.successfully),data = repository.getAllAssociate()))
            } catch (exception: Exception) {
                emit(Resource.error(message = exception.message ?: "Error Occurred!",data = null))
            }
        }
    }

    fun addProject(projectEntity: ProjectEntity, context: Context) = liveData(Dispatchers.IO) {

        if (projectEntity.projectname.isEmpty()) {
            emit(
                Resource.error(
                    data = null,
                    message = context.resources.getString(R.string.emptyProject)
                )
            )
        }
        else
            emit(
                Resource.success(
                    message = context.resources.getString(R.string.project_successfully),
                    data = repository.AddProject(projectEntity)
                )
            )

    }

    fun fetchProjectList(context: Context) : LiveData<Resource<List<ProjectEntity>>> {
        return  liveData(Dispatchers.IO) {

            try {
                emit(Resource.success("",data = repository.getAllProject()))
            } catch (exception: Exception) {
                emit(Resource.error(message = exception.message ?: "Error Occurred!",data = null))
            }
        }
    }

    fun deleteAssociate(associateEntity: AssociateEntity, context: Context) = liveData(Dispatchers.IO) {


            emit(
                Resource.success(
                    message = context.resources.getString(R.string.deleted_successfully),
                    data = repository.deleteAssociate(associateEntity)
                )
            )

    }
}





