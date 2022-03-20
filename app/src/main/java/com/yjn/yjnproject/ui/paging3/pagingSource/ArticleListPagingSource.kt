package com.yjn.yjnproject.ui.paging3.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yjn.common.util.T
import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.data.repository.DiscoverRepository
import com.yjn.yjnproject.ui.viewModel.DiscoverViewModel

class ArticleListPagingSource(val viewModel: DiscoverViewModel) : PagingSource<Int, ArticleList.DataX>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleList.DataX>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        T.show("refresh")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleList.DataX> {
        try {
            // Start refresh at page 0 if undefined.
            val nextPageNumber = params.key ?: 0
            val articleList = DiscoverRepository.getArticleList(viewModel,nextPageNumber)
            if (articleList != null) {
                return LoadResult.Page(
                    data = articleList.data.datas,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
                )
            }
            return LoadResult.Error(Throwable())
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return LoadResult.Error(e)
        }
    }
}