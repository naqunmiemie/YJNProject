package com.yjn.yjnproject.ui.fragment

import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.KeyboardUtils
import com.google.android.flexbox.FlexboxLayoutManager
import com.yjn.common.util.L
import com.yjn.yjnproject.data.entity.Hotkey
import com.yjn.yjnproject.databinding.FragmentHomeBinding
import com.yjn.yjnproject.ui.adapter.ArticleQueryRecycleViewAdapter
import com.yjn.yjnproject.ui.adapter.HotkeyRecycleViewAdapter
import com.yjn.common.base.BaseFragment
import com.yjn.common.base.BaseViewEvent
import com.yjn.yjnproject.ui.viewEvent.HomeViewEvent
import com.yjn.yjnproject.ui.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collect


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    lateinit var homeViewModel: HomeViewModel

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        homeViewModel = getFragmentScopeViewModel(HomeViewModel::class.java)

        binding.srlHome.setOnRefreshListener {
            homeViewModel.sendEvent(BaseViewEvent.RefreshEvent)
            binding.srlHome.isRefreshing = false
        }

        binding.ivSearch.setOnClickListener{
            binding.cetSearch.requestFocus()
            KeyboardUtils.showSoftInput()
        }

        binding.cetSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!TextUtils.isEmpty(v.text.toString())) {
                    homeViewModel.sendEvent(HomeViewEvent.ArticleQueryEvent(v.text.toString()))
                    KeyboardUtils.hideSoftInput(v)
                    v.text = ""
                    v.clearFocus()
                }
            }
            true
        }


        binding.rvHotkey.layoutManager = FlexboxLayoutManager(activity)
        binding.rvHotkey.adapter = HotkeyRecycleViewAdapter().apply {
            setOnItemClickListener(object : HotkeyRecycleViewAdapter.ItemClickListener {
                override fun onItemClick(data: Hotkey.Data, int: Int) {
                    homeViewModel.sendEvent(HomeViewEvent.ArticleQueryEvent(data.name))
                }
            })
        }

        binding.rvArticleQuery.layoutManager = LinearLayoutManager(activity)
        binding.rvArticleQuery.adapter = ArticleQueryRecycleViewAdapter()

        lifecycleScope.launchWhenStarted {
            homeViewModel.viewState.collect {
                L.d("data changed")
                (binding.rvHotkey.adapter as HotkeyRecycleViewAdapter).setDataList(it.hotkey?.data)
                (binding.rvArticleQuery.adapter as ArticleQueryRecycleViewAdapter).setDataList(it.articleList?.data?.datas)

//                GlideApp.with(this@HomeFragment)
//                    .load(it.githubUser?.avatar_url)
//                    .fitCenter()
//                    .circleCrop()
//                    .into(binding.ivAvatarUrl)

            }
        }
    }

    override fun initData() {
        homeViewModel.sendEvent(BaseViewEvent.InitEvent)
    }
}