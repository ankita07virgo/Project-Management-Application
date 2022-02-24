package com.test.projectmanagementapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.FragmentAssociateBinding
import com.test.projectmanagementapplication.databinding.FragmentProjectBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import com.test.projectmanagementapplication.roomdatabase.Status
import com.test.projectmanagementapplication.utils.ApplicationClass
import com.test.projectmanagementapplication.viewmodel.AssociateViewModel
import com.test.projectmanagementapplication.viewmodel.AssociateViewModelFactory

class ProjectFragment : Fragment() {
    private var fragmentProjectBinding: FragmentProjectBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = fragmentProjectBinding
    private lateinit var mProjectAdapter: ProjectAdapter
    private lateinit var  mAssociateViewModel: AssociateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProjectBinding = FragmentProjectBinding.inflate(inflater, container, false)
        val view = binding?.root
        mAssociateViewModel = ViewModelProvider(this, AssociateViewModelFactory((activity?.application as ApplicationClass).repository)).get(
            AssociateViewModel::class.java
        )
        setUI()
        handleClickListner()
        setUpRecyclerView()
        handleObserver()
        return  view
    }

    private fun handleObserver() {
        mAssociateViewModel.fetchProjectList(context!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.Success -> {
                        resource.data?.let { projects -> retrieveList(resource.data) }
                    }
                    Status.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }

    private fun retrieveList(project: List<ProjectEntity>) {
        mProjectAdapter.apply {
            addProject(project)
            notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.projectRecyclerView?.setHasFixedSize(true)
        binding?.projectRecyclerView?.layoutManager = linearLayoutManager
        mProjectAdapter = ProjectAdapter(activity?.applicationContext,
            arrayListOf()
        )
        binding?.projectRecyclerView?.adapter = mProjectAdapter
    }
    private fun setUI() {
        binding?.mainheaderLayout?.tvMainheader?.text = resources.getString(R.string.projects)
    }

    private fun handleClickListner() {
        binding?.mainheaderLayout?.imgAddAssociate?.setOnClickListener {
            launchFragment(AddProjectFragment.newInstance(),resources.getString(R.string.add_associate))
        }
    }

    private fun launchFragment(fragment: Fragment, tag: String) {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.app_container, fragment, tag)
        ft?.addToBackStack(null)
        ft?.commit()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            ProjectFragment().apply {

            }
    }
}