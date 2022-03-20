package com.yjn.yjnproject.data.repository

import androidx.lifecycle.viewModelScope
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.yjn.common.util.L
import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.data.entity.Hotkey
import com.yjn.yjnproject.data.net.api.ArticleQueryApi
import com.yjn.yjnproject.data.net.api.HotkeyApi
import com.yjn.yjnproject.ui.viewModel.HomeViewModel
import kotlinx.coroutines.launch

object HomeRepository {
    private var k = "面试"

    fun initEvent(viewModel: HomeViewModel){
        articleQuery(viewModel)
        hotkey(viewModel)
    }

    fun articleQueryEvent(viewModel: HomeViewModel,keyword: String){
        k = keyword
        articleQuery(viewModel)
    }

    fun refreshEvent(viewModel: HomeViewModel){
        articleQuery(viewModel)
        hotkey(viewModel)
    }

    private fun hotkey(viewModel: HomeViewModel){
        EasyHttp.get(viewModel)
            .api(HotkeyApi())
            .request(object :OnHttpListener<Hotkey?>{
                override fun onSucceed(hotkey: Hotkey?) {
                    if (hotkey != null){
                        viewModel.setState {
                            copy(
                                hotkey = hotkey
                            )
                        }
                    }
                }

                override fun onFail(e: Exception?) {
                }

            })
    }

    private fun articleQuery(viewModel: HomeViewModel){
        EasyHttp.post(viewModel)
            .api(ArticleQueryApi().apply {
                this.keyword = k
            })
            .request(object :OnHttpListener<ArticleList>{
                override fun onSucceed(articleList: ArticleList?) {
                    if (articleList != null){
                        viewModel.viewModelScope.launch {
                            L.d("initUserEvent viewModel.setState(HomeViewState)")
                            viewModel.setState {
                                copy(
                                    articleList = articleList
                                )
                            }
                        }
                    }
                }

                override fun onFail(e: java.lang.Exception?) {
                }

            })

    }


}