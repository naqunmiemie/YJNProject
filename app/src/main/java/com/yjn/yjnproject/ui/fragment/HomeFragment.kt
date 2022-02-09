package com.yjn.yjnproject.ui.fragment

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yjn.common.util.L
import com.yjn.yjnproject.databinding.FragmentHomeBinding
import com.yjn.yjnproject.ui.base.BaseFragment
import com.yjn.yjnproject.ui.viewEvent.HomeViewEvent
import com.yjn.yjnproject.ui.viewModel.HomeViewModel
import com.yjn.yjnproject.ui.widget.glide.GlideApp
import kotlinx.coroutines.flow.collect

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var homeViewModel: HomeViewModel

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        homeViewModel = getFragmentScopeViewModel(HomeViewModel::class.java)

        binding.srlHome.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            homeViewModel.sendEvent(HomeViewEvent.RefreshUserEvent)
            binding.srlHome.isRefreshing = false
        })
        lifecycleScope.launchWhenStarted {
            homeViewModel.viewState.collect {
                L.d("data changed")
                GlideApp.with(this@HomeFragment)
                    .load(it.githubUser?.avatar_url)
                    .fitCenter()
                    .circleCrop()
                    .into(binding.ivAvatarUrl)
                binding.tvCreatedAt.text = it.githubUser?.created_at
                binding.tvFollowers.text = it.githubUser?.followers.toString()
                binding.tvHtmlUrl.text = it.githubUser?.html_url
                binding.tvLogin.text = it.githubUser?.login
                binding.tvType.text = it.githubUser?.type
                binding.tvUpdatedAt.text = it.githubUser?.updated_at
                binding.tvUrl.text = it.githubUser?.url
            }
        }
        binding.btnOk.setOnClickListener(View.OnClickListener {
            val inputUserName = binding.etInput.text.toString()
            if (!TextUtils.isEmpty(inputUserName)){
                homeViewModel.sendEvent(HomeViewEvent.GetUserEvent(inputUserName))
            }
        })
    }

    override fun initData() {
        homeViewModel.sendEvent(HomeViewEvent.InitUserEvent)
    }
}