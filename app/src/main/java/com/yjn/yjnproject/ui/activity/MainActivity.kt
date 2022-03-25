package com.yjn.yjnproject.ui.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.yjn.yjnproject.R
import com.yjn.yjnproject.databinding.ActivityMainBinding
import com.yjn.common.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun initView() {
        //底部导航栏icon颜色不随主题色改变
        binding.bnvMain.itemIconTintList = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bnvMain,navController)
    }



    override fun initData() {
    }
}