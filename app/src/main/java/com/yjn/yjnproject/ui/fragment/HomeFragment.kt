package com.yjn.yjnproject.ui.fragment

import androidx.lifecycle.lifecycleScope
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
        L.d("!!!")

        homeViewModel = getFragmentScopeViewModel(HomeViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            homeViewModel.viewState.collect {
                L.d("data changed")
                GlideApp.with(this@HomeFragment)
                    .load(it.user?.avatar_url)
                    .fitCenter()
                    .circleCrop()
                    .into(binding.ivAvatarUrl)
                binding.tvCreatedAt.text = it.user?.created_at
                binding.tvFollowers.text = it.user?.followers.toString()
                binding.tvFollowing.text = it.user?.following.toString()
                binding.tvHtmlUrl.text = it.user?.html_url
                binding.tvLogin.text = it.user?.login
                binding.tvPublicGists.text = it.user?.public_gists.toString()
                binding.tvPublicRepos.text = it.user?.public_repos.toString()
                binding.tvType.text = it.user?.type
                binding.tvUpdatedAt.text = it.user?.updated_at
                binding.tvUrl.text = it.user?.url
            }
        }
    }

    override fun initData() {
        homeViewModel.sendEvent(HomeViewEvent.InitUserEvent)
    }
}