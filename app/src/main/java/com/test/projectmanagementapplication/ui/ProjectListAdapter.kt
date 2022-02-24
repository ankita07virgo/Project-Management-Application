package com.test.projectmanagementapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import kotlin.collections.ArrayList


class ProjectListAdapter(ctx: Context, projectList: ArrayList<ProjectEntity>) : ArrayAdapter<ProjectEntity>(ctx, 0, projectList) {

    private lateinit var projectList : ArrayList<ProjectEntity>
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createDropItemView(position, convertView, parent);
    }

    fun createItemView(position: Int, recycledView: View?, parent: ViewGroup):View {
        val project = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.projectspinnerlayout,
            parent,
            false
        )
        val tv : TextView = view.findViewById(R.id.spinnertv_projectname)

        project?.let {
            tv.text = project.projectname
        }
        return view
    }

    fun addProject(projectEntityList: List<ProjectEntity>) {
        this.projectList.apply {
            clear()
            addAll(projectEntityList)

        }
    }

    fun createDropItemView(position: Int, recycledView: View?, parent: ViewGroup):View {
        val project = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.row_spinner_dialog,
            parent,
            false
        )
        val tv : TextView = view.findViewById(R.id.text1)

        project?.let {
            tv.text = project.projectname
        }
        return view
    }

    init {
        this.projectList = projectList
    }
}
