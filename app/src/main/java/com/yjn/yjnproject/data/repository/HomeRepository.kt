package com.yjn.yjnproject.data.repository

import androidx.lifecycle.viewModelScope
import com.hjq.http.EasyHttp
import com.hjq.http.listener.OnHttpListener
import com.yjn.common.util.L
import com.yjn.yjnproject.data.model.User
import com.yjn.yjnproject.data.net.api.GetUserApi
import com.yjn.yjnproject.ui.viewModel.HomeViewModel
import com.yjn.yjnproject.ui.viewState.HomeViewState
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
            .request(object :OnHttpListener<User?>{
                override fun onSucceed(user: User?) {
                    if (user != null){
                        viewModel.viewModelScope.launch {
                            L.d("initUserEvent viewModel.setState(HomeViewState)")
                            viewModel.setState {
                                copy(
                                    user = user
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