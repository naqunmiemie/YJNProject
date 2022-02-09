package com.yjn.yjnproject.data.repository

import com.hjq.http.EasyHttp
import com.hjq.http.EasyLog
import com.hjq.http.model.ResponseClass
import com.yjn.common.util.T
import com.yjn.yjnproject.data.entity.WAndroid
import com.yjn.yjnproject.data.net.api.SearchAuthorApi
import com.yjn.yjnproject.ui.viewModel.DiscoverViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object DiscoverRepository {
    suspend fun searchAuthor(viewModel: DiscoverViewModel,nextPageNumber: Int) : WAndroid?{
        return withContext(Dispatchers.IO){
            val getRequest = EasyHttp.get(viewModel)
            val nextPageNumber = 19000+nextPageNumber
            try {
                val data : WAndroid? = getRequest
                    .api(SearchAuthorApi().setId(nextPageNumber))
                    .execute<WAndroid>(object : ResponseClass<WAndroid>() {})
                T.show("请求成功$nextPageNumber")
                return@withContext data
            } catch (e: Exception) {
                EasyLog.printThrowable(getRequest, e)
                T.show(e.message)
                return@withContext null
            }
        }
//        var data : WAndroid? = null
//        viewModel.viewModelScope.launch {
//            val getRequest = EasyHttp.get(viewModel)
//            try {
//                data = getRequest
//                    .api(SearchAuthorApi().setId(19000))
//                    .execute<WAndroid>(object : ResponseClass<WAndroid>() {})
//                T.show("请求成功，请看日志")
//            } catch (e: Exception) {
//                EasyLog.printThrowable(getRequest, e)
//                T.show(e.message)
//            }
//        }
//        return data
    }
}