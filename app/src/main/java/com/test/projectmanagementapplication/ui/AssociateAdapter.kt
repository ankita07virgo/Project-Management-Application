package com.test.projectmanagementapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.AdapterAssociateListBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import kotlin.collections.ArrayList

class AssociateAdapter(
    context: Context?,
    associateEntityList: ArrayList<AssociateEntity>) : RecyclerView.Adapter<AssociateAdapter.MyViewHolder>() {

    private val associateEntityList: ArrayList<AssociateEntity> = ArrayList<AssociateEntity>()
    private val context: Context?
    fun addAssociate(associateEntityList: List<AssociateEntity>) {
        this.associateEntityList.apply {
            clear()
            addAll(associateEntityList)

        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {

        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_associate_list, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        val associateEntity: AssociateEntity = associateEntityList[i]
               with(viewHolder) {
                   binding.tvAssociateName.text = associateEntity.empName+ " ("+associateEntity.empBand +")"
                   binding.tvEmpId.text = associateEntity.empID
                   binding.tvAssociateDesignation.text = associateEntity.empDesignation
                   binding.tvAssociateDepartment.text = associateEntity.empDepartment
                   binding.tvAssociateProject.text = associateEntity.empProject






        }
    }

    override fun getItemCount(): Int {
        return associateEntityList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



    init {
        this.associateEntityList.addAll(associateEntityList)
        this.context = context
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = AdapterAssociateListBinding.bind(itemView)
    }

}