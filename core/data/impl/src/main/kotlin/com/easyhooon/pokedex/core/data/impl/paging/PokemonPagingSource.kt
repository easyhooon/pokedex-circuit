package com.easyhooon.pokedex.core.data.impl.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.easyhooon.pokedex.core.data.impl.util.Constants.PAGING_SIZE
import com.easyhooon.pokedex.core.data.impl.util.Constants.STARTING_PAGE_INDEX
import com.easyhooon.pokedex.core.network.response.Pokemon
import com.easyhooon.pokedex.core.network.service.PokemonService
import timber.log.Timber
import java.io.IOException

class PokemonPagingSource(
    private val service: PokemonService,
) : PagingSource<Int, Pokemon>() {

    // PagingDataAdapter.refresh() or LazyPagingItems.refresh() 와 같은 함수를 통해 수동으로 리프레시를 할 때 호출됨
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
        return null
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPokemonList(
                limit = params.loadSize,
                offset = position,
            )
            val endOfPaginationReached = response.results.isEmpty()
            val data = response.results
            val nextKey = if (endOfPaginationReached) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / PAGING_SIZE)
            }
            LoadResult.Page(
                data = data,
                // prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}
