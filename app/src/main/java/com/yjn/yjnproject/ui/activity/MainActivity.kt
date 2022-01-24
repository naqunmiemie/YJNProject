package com.yjn.yjnproject.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.yjn.yjnproject.R
import com.yjn.yjnproject.databinding.ActivityMainBinding
import com.yjn.yjnproject.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initView()
        initData()
    }

    private fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        NavigationUI.setupWithNavController(binding.bnvMain,navController)
    }



    private fun initData() {
    }
}