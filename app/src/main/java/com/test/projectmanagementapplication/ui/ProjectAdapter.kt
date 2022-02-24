package com.test.projectmanagementapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.AdapterAssociateListBinding
import com.test.projectmanagementapplication.databinding.AdapterProjectListBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import kotlin.collections.ArrayList

class ProjectAdapter(
    context: Context?,
    projectEntityList: ArrayList<ProjectEntity>) : RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

    private val projectEntityList: ArrayList<ProjectEntity> = ArrayList<ProjectEntity>()
    private val context: Context?
    fun addProject(projectEntityList: List<ProjectEntity>) {
        this.projectEntityList.apply {
            addAll(projectEntityList)

        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {

        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_project_list, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        val projectEntity: ProjectEntity = projectEntityList[i]
               with(viewHolder) {
                   binding.tvProjectname.text = projectEntity.projectname

        }
    }

    override fun getItemCount(): Int {
        return projectEntityList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



    init {
        this.projectEntityList.addAll(projectEntityList)
        this.context = context
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = AdapterProjectListBinding.bind(itemView)
    }

}