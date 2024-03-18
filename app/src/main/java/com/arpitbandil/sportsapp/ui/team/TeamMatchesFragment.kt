package com.arpitbandil.sportsapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arpitbandil.sportsapp.databinding.FragmentTeamMatchesBinding

class TeamMatchesFragment : Fragment() {

    private lateinit var binding: FragmentTeamMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTeamMatchesBinding.inflate(inflater).apply {
        binding = this
        binding.btnMatches.setOnClickListener {
            findNavController().navigate(
                TeamDetailsFragmentDirections.actionMatches()
            )
        }
    }.root
}