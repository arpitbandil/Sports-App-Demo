package com.arpitbandil.sportsapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.databinding.FragmentTeamOverviewBinding
import com.arpitbandil.sportsapp.generators.Generator.generatePlayers
import com.arpitbandil.sportsapp.modal.Player

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentTeamOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTeamOverviewBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvPlayers.adapter = MiniPlayerAdapter(generatePlayers(), ::onItemClick)

    }

    private fun onItemClick(
        position: Int,
        selectedItem: Player,
        sharedElements: Map<View, String>
    ) {
    }
//        findNavController().navigate(
//            RatingsFragmentDirections.actionTeamDetails(
//                selectedItem,
//                position
//            ),
//            navigatorExtras = FragmentNavigatorExtras(*sharedElements.map { it.key to it.value }
//                .toTypedArray())
//        )
}