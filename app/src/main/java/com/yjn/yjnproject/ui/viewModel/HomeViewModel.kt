package com.yjn.yjnproject.ui.viewModel

import com.yjn.yjnproject.data.repository.HomeRepository
import com.yjn.yjnproject.ui.base.BaseViewEvent
import com.yjn.yjnproject.ui.base.BaseViewModelMVI
import com.yjn.yjnproject.ui.viewEvent.HomeViewEvent
import com.yjn.yjnproject.ui.viewState.HomeViewState

class HomeViewModel : BaseViewModelMVI<HomeViewState>() {
    override fun providerInitialState(): HomeViewState {
        return HomeViewState()
    }

    override fun handleEvent(viewEvent: Any) {
        when(viewEvent){
            is BaseViewEvent.InitEvent -> HomeRepository.initEvent(this@HomeViewModel)
            is HomeViewEvent.ArticleQueryEvent -> HomeRepository.articleQueryEvent(this@HomeViewModel,viewEvent.keyword)
            is BaseViewEvent.RefreshEvent -> HomeRepository.refreshEvent(this@HomeViewModel)
        }
    }
}