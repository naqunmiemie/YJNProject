package com.yjn.yjnproject.ui.fragment

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yjn.yjnproject.databinding.FragmentDiscoverBinding
import com.yjn.yjnproject.ui.adapter.ArticleListRecycleViewAdapter
import com.yjn.yjnproject.ui.base.BaseFragment
import com.yjn.yjnproject.ui.viewModel.DiscoverViewModel
import kotlinx.coroutines.flow.collect


class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    lateinit var discoverViewModel: DiscoverViewModel
    companion object {
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }

    override fun initView() {
        discoverViewModel = getFragmentScopeViewModel(DiscoverViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)
        binding.rvWAndroid.layoutManager = layoutManager
        val adapter = ArticleListRecycleViewAdapter()
        binding.rvWAndroid.adapter =  adapter

        lifecycleScope.launchWhenStarted {
            discoverViewModel.getData().collect {
                adapter.submitData(it)
            }
        }

        binding.srlDiscover.setOnRefreshListener {
            adapter.refresh()
            binding.srlDiscover.isRefreshing = false
        }
    }

    override fun initData() {
    }
}