package com.yjn.yjnproject.ui.paging3.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yjn.yjnproject.data.entity.WAndroid
import com.yjn.yjnproject.data.repository.DiscoverRepository
import com.yjn.yjnproject.ui.viewModel.DiscoverViewModel

class WAndroidUserPagingSource(val viewModel: DiscoverViewModel) : PagingSource<Int, WAndroid.WAndroidUser>() {
    override fun getRefreshKey(state: PagingState<Int, WAndroid.WAndroidUser>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WAndroid.WAndroidUser> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val wAndroid = DiscoverRepository.searchAuthor(viewModel,nextPageNumber)
            if (wAndroid != null) {
                return LoadResult.Page(
                    data = wAndroid.data,
                    prevKey = null, // Only paging forward.
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