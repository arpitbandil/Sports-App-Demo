package com.arpitbandil.sportsapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
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
        binding.rvPlayers.adapter = MiniPlayerAdapter(generatePlayers(), ::onItemClick)
    }

    private fun onItemClick(
        position: Int,
        selectedItem: Player,
        sharedElements: Map<View, String>
    ) =
        findNavController().navigate(
            TeamDetailsFragmentDirections.playerDetails(
                selectedItem,
                position
            ),
            navigatorExtras = FragmentNavigatorExtras(*sharedElements.map { it.key to it.value }
                .toTypedArray())
        )
}