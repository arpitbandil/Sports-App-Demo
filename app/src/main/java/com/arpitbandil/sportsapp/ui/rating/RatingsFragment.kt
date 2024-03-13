package com.arpitbandil.sportsapp.ui.rating

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentRatingsBinding
import com.arpitbandil.sportsapp.modal.Team
import com.arpitbandil.sportsapp.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RatingsFragment : Fragment(R.layout.fragment_ratings) {

    private val binding by viewBinding(FragmentRatingsBinding::bind)
    private val miniAdapter = MiniTeamAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        postponeEnterTransition()
        binding.rvTeamRank.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        binding.rvTeamRank.adapter = miniAdapter
        lifecycleScope.launch {
            miniAdapter.loadStateFlow.collect {
                binding.appendProgress.isVisible =
                    it.source.prepend is LoadState.Loading || it.source.append is LoadState.Loading
            }
        }
        lifecycleScope.launch {
            items.collectLatest {
                miniAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(position: Int, selectedItem: Team, sharedElements: Map<View, String>) =
        findNavController().navigate(
            RatingsFragmentDirections.actionTeamDetails(
                selectedItem,
                position
            ),
            navigatorExtras = FragmentNavigatorExtras(*sharedElements.map { it.key to it.value }
                .toTypedArray())
        )
}