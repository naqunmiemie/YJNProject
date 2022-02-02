package com.yjn.yjnproject.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.yjn.common.util.L
import com.yjn.yjnproject.R
import com.yjn.yjnproject.databinding.ActivityMainBinding
import com.yjn.yjnproject.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        L.d("!!!")
//    }

    override fun initView() {
        L.d("!!!")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bnvMain,navController)
    }



    override fun initData() {
    }
}