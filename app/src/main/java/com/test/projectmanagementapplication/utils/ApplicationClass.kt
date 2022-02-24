package com.test.projectmanagementapplication.utils

import android.app.Application
import com.test.projectmanagementapplication.repository.Repository
import com.test.projectmanagementapplication.roomdatabase.AppDataBase

class ApplicationClass : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDataBase.getDatabaseInstance(this) }
    val repository by lazy { Repository(database) }
}