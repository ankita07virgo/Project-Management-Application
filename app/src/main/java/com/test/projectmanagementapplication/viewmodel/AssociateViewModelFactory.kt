package com.test.projectmanagementapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.projectmanagementapplication.repository.Repository

class AssociateViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssociateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssociateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}