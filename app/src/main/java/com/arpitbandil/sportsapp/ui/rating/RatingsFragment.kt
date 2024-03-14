package com.arpitbandil.sportsapp.ui.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.arpitbandil.sportsapp.databinding.FragmentRatingsBinding
import com.arpitbandil.sportsapp.modal.Team
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RatingsFragment : Fragment() {

    private val miniAdapter = MiniTeamAdapter(::onItemClick)
    private lateinit var binding: FragmentRatingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRatingsBinding.inflate(inflater).apply {
        binding = this
    }.root

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