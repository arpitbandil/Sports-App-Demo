package com.arpitbandil.sportsapp.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentPlayerOverviewBinding

class ProfileSocialFragment : Fragment() {

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

    @SuppressLint("SetTextI18n")
    private fun setupUI() = binding.apply {
        tvName.text = getString(R.string.facebook)
        tvBirth.text = getString(R.string.instagram)
        tvCountry.text = getString(R.string.x_twitter)
        tvBio.text = getString(R.string.linkedin)
        ivFlag.isVisible = false
        tvBirthValue.text = "https://www.instagram.com/arpitbandil"
        tvCountryValue.text = "https://twitter.com/arpitbandil"
        tvNameValue.text = "https://www.facebook.com/bandil.arpit/"
        tvBioValue.text = "https://www.linkedin.com/in/arpit-bandil-221bb275"
    }
}