package com.yjn.yjnproject.data.repository

import com.hjq.http.EasyHttp
import com.hjq.http.EasyLog
import com.hjq.http.model.ResponseClass
import com.hjq.toast.ToastUtils
import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.data.net.api.ArticleListApi
import com.yjn.yjnproject.ui.viewModel.DiscoverViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object DiscoverRepository {
    suspend fun getArticleList(viewModel: DiscoverViewModel,nextPageNumber: Int) : ArticleList?{
        return withContext(Dispatchers.IO){
            val getRequest = EasyHttp.get(viewModel)
            try {
                val data : ArticleList? = getRequest
                    .api(ArticleListApi().apply {
                        pageSize = nextPageNumber
                    })
                    .execute(object : ResponseClass<ArticleList>() {})
                ToastUtils.show("请求成功 nextPageNumber = $nextPageNumber")
                return@withContext data
            } catch (e: Exception) {
                EasyLog.printThrowable(getRequest, e)
                ToastUtils.show(e.message)
                return@withContext null
            }
        }
    }
}