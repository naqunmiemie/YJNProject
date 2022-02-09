package com.yjn.yjnproject.data.repository

import androidx.lifecycle.viewModelScope
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.yjn.common.util.L
import com.yjn.yjnproject.data.entity.GithubUser
import com.yjn.yjnproject.data.net.api.GetUserApi
import com.yjn.yjnproject.ui.viewModel.HomeViewModel
import kotlinx.coroutines.launch

object HomeRepository {
    var userName = "getActivity"
    fun initUserEvent(viewModel: HomeViewModel){
        getUser(viewModel)
    }

    fun getUserEvent(viewModel: HomeViewModel,inputUserName: String){
        userName = inputUserName
        getUser(viewModel)
    }

    fun refreshUserEvent(viewModel: HomeViewModel){
        getUser(viewModel)
    }

    private fun getUser(viewModel: HomeViewModel){
        EasyHttp.get(viewModel)
            .api(GetUserApi().setUserId(userName))
            .request(object :OnHttpListener<GithubUser?>{
                override fun onSucceed(githubUser: GithubUser?) {
                    if (githubUser != null){
                        viewModel.viewModelScope.launch {
                            L.d("initUserEvent viewModel.setState(HomeViewState)")
                            viewModel.setState {
                                copy(
                                    githubUser = githubUser
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