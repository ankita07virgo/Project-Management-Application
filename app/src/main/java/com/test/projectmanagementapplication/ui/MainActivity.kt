package com.test.projectmanagementapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.projectmanagementapplication.R
import com.test.projectmanagementapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intializeView()
        handleBottomNavigation()
        launchFragment(AssociateFragment.newInstance(),resources.getString(R.string.associate))
    }

    private fun handleBottomNavigation() {
        mBinding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_associate ->launchFragment(
                    AssociateFragment.newInstance(),resources.getString(
                        R.string.associate
                    ))
                R.id.navigation_project ->launchFragment(
                    ProjectFragment.newInstance(),resources.getString(
                        R.string.projects
                    ))

            }
            true
        }
    }

    private fun launchFragment(fragment: Fragment, tag: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.app_container, fragment, tag)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun intializeView() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
    }
}