package com.yjn.yjnproject.ui.activity

import android.os.Bundle
import com.yjn.yjnproject.R
import com.yjn.yjnproject.databinding.ActivityMainBinding
import com.yjn.yjnproject.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}