package com.yjn.yjnproject.ui.viewState

import com.yjn.yjnproject.data.entity.GithubUser
import com.yjn.yjnproject.ui.base.BaseViewState

data class HomeViewState(
    var githubUser: GithubUser?
):BaseViewState()
