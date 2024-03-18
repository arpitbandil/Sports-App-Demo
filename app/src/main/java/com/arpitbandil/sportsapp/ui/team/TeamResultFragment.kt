package com.arpitbandil.sportsapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentNotificationsBinding

class TeamResultFragment(val name: String) : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentNotificationsBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = getString(R.string.no_results_found_yet_for_this_team, name)
    }
}