package com.arpitbandil.sportsapp.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentPlayerOverviewBinding

class PlayerSocialFragment : Fragment() {

    private lateinit var binding: FragmentPlayerOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlayerOverviewBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = binding.apply {
        tvName.text = getString(R.string.facebook)
        tvBirth.text = getString(R.string.instagram)
        tvCountry.text = getString(R.string.x_twitter)
        tvBio.text = getString(R.string.whatsapp)
        ivFlag.isVisible = false
        tvBirthValue.text = getString(R.string.https_www_instagram_com)
        tvCountryValue.text = getString(R.string.https_twitter_com)
        tvNameValue.text = getString(R.string.https_www_facebook_com)
        tvBioValue.text = getString(R.string.https_www_whatsapp_com)
    }
}