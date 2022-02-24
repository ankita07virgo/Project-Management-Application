package com.test.projectmanagementapplication.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


 @Database(entities = [AssociateEntity::class,ProjectEntity::class],version = 1)
 public abstract class AppDataBase: RoomDatabase() {
    companion object{
        var appdataBase: AppDataBase?=null
        val databasename:String="projectMGMT_db"

        /*-----------INSTANTIATING DATABASE AS SIGNLETON------------*/
        fun  getDatabaseInstance(context: Context): AppDataBase {
            if(appdataBase ==null){
                appdataBase = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, databasename).build()

            }
            return appdataBase as AppDataBase
        }
    }

    abstract fun appdao(): AssociateDao

}