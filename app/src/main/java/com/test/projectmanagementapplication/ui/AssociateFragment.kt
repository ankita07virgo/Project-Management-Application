package com.test.projectmanagementapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.FragmentAssociateBinding
import com.test.projectmanagementapplication.roomdatabase.AssociateEntity
import com.test.projectmanagementapplication.roomdatabase.ProjectEntity
import com.test.projectmanagementapplication.roomdatabase.Status
import com.test.projectmanagementapplication.utils.ApplicationClass
import com.test.projectmanagementapplication.viewmodel.AssociateViewModel
import com.test.projectmanagementapplication.viewmodel.AssociateViewModelFactory
import android.app.ListActivity

import androidx.recyclerview.widget.RecyclerView





class AssociateFragment : Fragment() {
    private var fragmentAssociateBinding: FragmentAssociateBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = fragmentAssociateBinding
    private lateinit var mAssociateAdapter: AssociateAdapter
    private lateinit var  mAssociateViewModel: AssociateViewModel
    private lateinit var associateList : ArrayList<AssociateEntity>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAssociateBinding = FragmentAssociateBinding.inflate(inflater, container, false)
        val view = binding?.root
        mAssociateViewModel = ViewModelProvider(this, AssociateViewModelFactory((activity?.application as ApplicationClass).repository)).get(
            AssociateViewModel::class.java
        )
        handleClickListner()
        setUpRecyclerView()
       handleObserver()
        handleRecyclerView()

        return  view
    }

    private fun handleRecyclerView() {

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Toast.makeText(requireContext(), "on Move", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                Toast.makeText(requireContext(), "on Swiped ", Toast.LENGTH_SHORT).show()
                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition


                mAssociateViewModel.deleteAssociate(associateList.get(position),context!!).observe(requireActivity(), Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.Success -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.Error -> {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                            }

                        }
                    }
                })
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding?.associateRecyclerView)
}


    private fun handleObserver() {
        mAssociateViewModel.fetchAssociateList(context!!).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.Success -> {
                        resource.data?.let { newsBase -> retrieveList(resource.data) }
                    }
                    Status.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }

                }
            }
        })

    }



    private fun retrieveList(associate: List<AssociateEntity>) {
        associateList = ArrayList<AssociateEntity>()
        associateList.addAll(associate)
        mAssociateAdapter.apply {
            addAssociate(associate)
            notifyDataSetChanged()
        }
    }
    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity?.applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.associateRecyclerView?.setHasFixedSize(true)
        binding?.associateRecyclerView?.layoutManager = linearLayoutManager
        mAssociateAdapter = AssociateAdapter(activity?.applicationContext,
            arrayListOf()
            )
        binding?.associateRecyclerView?.adapter = mAssociateAdapter
    }

    private fun handleClickListner() {
        binding?.mainheaderLayout?.imgAddAssociate?.setOnClickListener {
            launchFragment(AddAssociateFragment.newInstance(),resources.getString(R.string.add_associate))
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
            AssociateFragment().apply {

            }
    }
}