package com.arpitbandil.sportsapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentPlayerOverviewBinding
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale

class ProfileOverviewFragment : Fragment() {

    private lateinit var binding: FragmentPlayerOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlayerOverviewBinding.inflate(inflater).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = binding.apply {
        tvBirthValue.text = getString(R.string.august_6_1996)
        tvCountryValue.text = Locale("", "IN").displayCountry
        tvNameValue.text = getString(R.string.arpit_bandil)
        ivFlag.setImageResource(FlagKit.getResId(requireContext(), "IN"))
        tvBioValue.text =
            getString(R.string.i_am_fullstack_developer_with_various_technologies_explored_expert_in_android_development_visit_https_github_com_arpitbandil_to_know_more)
    }
}