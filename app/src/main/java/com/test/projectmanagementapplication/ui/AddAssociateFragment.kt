package com.test.projectmanagementapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.FragmentAddAssociateBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.utils.ApplicationClass
import com.test.projectmanagementapplication.viewmodel.AssociateViewModel
import com.test.projectmanagementapplication.viewmodel.AssociateViewModelFactory

import androidx.lifecycle.Observer
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import com.test.projectmanagementapplication.roomdatabase.Status
import com.test.projectmanagementapplication.utils.CommonUtils


class AddAssociateFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentAddAssociateBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mCommonUtils : CommonUtils

    private lateinit var  mAssociateViewModel:  AssociateViewModel
    private lateinit var mSpinnerAdapter: ProjectListAdapter
    private lateinit var projectsList : ArrayList<ProjectEntity>
    private lateinit  var selectedProject : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAssociateBinding.inflate(inflater, container, false)
        val view = binding.root

        intializeMember()
        handleUI()
        setAdapter()
        handleObserver()
        return  view
    }

    private fun handleObserver() {

        mAssociateViewModel.fetchProjectList(context!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.Success -> {
                        resource.data?.let { updateAdapter(resource.data) }
                    }
                    Status.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }
    private fun updateAdapter(dataList: List<ProjectEntity>) {

        mSpinnerAdapter.apply {
            projectsList.addAll(dataList)
            addProject(dataList)
            notifyDataSetChanged()
        }

    }


    private fun setAdapter() {
        mSpinnerAdapter = ProjectListAdapter(activity?.applicationContext!!,
            projectsList
        )
        mSpinnerAdapter.setDropDownViewResource(R.layout.row_spinner_dialog)
        binding.spinnerProject.adapter = mSpinnerAdapter
    }
    private fun intializeMember() {
        mAssociateViewModel = ViewModelProvider(this, AssociateViewModelFactory((activity?.application as ApplicationClass).repository)).get(
            AssociateViewModel::class.java
        )
        mCommonUtils = CommonUtils()
        projectsList = ArrayList()
    }

    private fun handleUI() {
        binding.mainheaderLayout.imgAddAssociate.visibility = View.GONE
        binding.mainheaderLayout.tvMainheader.text = resources.getString(R.string.add_associate)

        binding.btnSubmit.setOnClickListener {
            val mAssociateEntity = AssociateEntity(binding.etEmployeeid.text.toString(),binding.etEmployeename.text.toString(),binding.etEmployeeband.text.toString(),binding.etEmployeedesignation.text.toString(),getSelectedDepartment(),selectedProject)
            mAssociateViewModel.addEmployee(mAssociateEntity,context!!).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.Success -> {
                            val drawable: Int = R.drawable.correct_icon
                            val color: Int = R.color.notiSuccessColor
                            mCommonUtils.showAlert(2000,resources.getString(R.string.app_name),resource.message,drawable,color,requireActivity())
                        }
                        Status.Error -> {
                            val drawable: Int = R.drawable.wrong_icon
                            val color: Int = R.color.notiFailColor
                            mCommonUtils.showAlert(2000,resources.getString(R.string.app_name),resource.message,drawable,color,requireActivity())
                        }

                    }
                }
            })
        }

        binding.spinnerProject.onItemSelectedListener = this
    }

    private fun getSelectedDepartment() : String {
        val selectedId: Int = binding.radioGroupDepartment.checkedRadioButtonId
        var selectedRadioBtn : String =resources.getString(R.string.android)
        when(selectedId){
            1 -> selectedRadioBtn = binding.rbAndroid.text.toString()
            2 -> selectedRadioBtn = binding.rbAndroid.text.toString()
            3 -> selectedRadioBtn = binding.rbAndroid.text.toString()
            4 -> selectedRadioBtn = binding.rbAndroid.text.toString()
        }
        return selectedRadioBtn

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddAssociateFragment().apply {
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position!= 0)
            selectedProject = projectsList.get(position).projectname
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedProject ="0"
    }
}