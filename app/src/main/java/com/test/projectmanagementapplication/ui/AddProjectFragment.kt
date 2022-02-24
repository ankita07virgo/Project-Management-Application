package com.test.projectmanagementapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.FragmentAddAssociateBinding
import com.test.projectmanagementapplication.databinding.FragmentAddProjectBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import com.test.projectmanagementapplication.roomdatabase.Status
import com.test.projectmanagementapplication.utils.ApplicationClass
import com.test.projectmanagementapplication.utils.CommonUtils
import com.test.projectmanagementapplication.viewmodel.AssociateViewModel
import com.test.projectmanagementapplication.viewmodel.AssociateViewModelFactory


class AddProjectFragment : Fragment() {
    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mAssociateViewModel: AssociateViewModel
    private lateinit var  mCommonUtils : CommonUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        val view = binding.root
        intializeMember()
        handleUI()
        return view
    }

    private fun intializeMember() {
        mAssociateViewModel = ViewModelProvider(this, AssociateViewModelFactory((activity?.application as ApplicationClass).repository)).get(
            AssociateViewModel::class.java
        )
        mCommonUtils = CommonUtils()
    }

    private fun handleUI() {
        binding.mainheaderLayout.imgAddAssociate.visibility = View.GONE
        binding.mainheaderLayout.tvMainheader.text = resources.getString(R.string.add_project)

        binding.btnSubmit.setOnClickListener {
            val mProjectEntity = ProjectEntity(binding.etProjectname.text.toString())
            mAssociateViewModel.addProject(mProjectEntity,context!!).observe(this, Observer {
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
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            AddProjectFragment().apply {

            }
    }
}