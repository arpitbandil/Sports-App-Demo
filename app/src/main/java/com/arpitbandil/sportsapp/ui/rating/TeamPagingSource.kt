package com.arpitbandil.sportsapp.ui.rating

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.arpitbandil.sportsapp.generators.Generator.generateTeams
import com.arpitbandil.sportsapp.modal.Team
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow


private const val ITEMS_PER_PAGE = 10
val items: Flow<PagingData<Team>> = Pager(
    config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
    pagingSourceFactory = { TeamPagingSource() }
).flow.cachedIn(GlobalScope)

class TeamPagingSource() : PagingSource<Int, Team>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Team>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Team> {
        val position = params.key ?: STARTING_PAGE_INDEX
        if (position != STARTING_PAGE_INDEX) delay(5000)
        return LoadResult.Page(
            data = generateTeams(params.loadSize),
            prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
            nextKey = position + 1
        )
    }
}
