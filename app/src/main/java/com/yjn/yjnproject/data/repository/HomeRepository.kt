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
    fun initUserEvent(viewModel: HomeViewModel){
        EasyHttp.get(viewModel)
            .api(GetUserApi().setUserId("getActivity"))
            .request(object :OnHttpListener<User?>{
                override fun onSucceed(user: User?) {
                    if (user != null){
                        viewModel.viewModelScope.launch {
                            L.d("viewModel.setState(HomeViewState)")
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

    fun getUserEvent(viewModel: HomeViewModel){

    }

    fun refreshUserEvent(viewModel: HomeViewModel){

    }


}