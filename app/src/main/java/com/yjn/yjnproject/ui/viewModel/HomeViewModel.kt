package com.yjn.yjnproject.ui.viewModel

import com.yjn.yjnproject.data.repository.HomeRepository
import com.yjn.yjnproject.ui.base.BaseViewModelMVI
import com.yjn.yjnproject.ui.viewEvent.HomeViewEvent
import com.yjn.yjnproject.ui.viewState.HomeViewState


class HomeViewModel : BaseViewModelMVI<HomeViewEvent, HomeViewState>() {
    override fun providerInitialState(): HomeViewState {
        return HomeViewState(null)
    }

    override fun handleEvent(viewEvent: HomeViewEvent) {
        when(viewEvent){
            is HomeViewEvent.InitUserEvent -> HomeRepository.initUserEvent(this@HomeViewModel)
            is HomeViewEvent.GetUserEvent -> HomeRepository.getUserEvent(this@HomeViewModel)
            is HomeViewEvent.RefreshUserEvent -> HomeRepository.refreshUserEvent(this@HomeViewModel)
        }
    }
}