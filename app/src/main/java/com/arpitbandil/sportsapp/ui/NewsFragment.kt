package com.arpitbandil.sportsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentHomeBinding
import com.arpitbandil.sportsapp.databinding.FragmentNotificationsBinding
import com.arpitbandil.sportsapp.viewBinding
import com.arpitbandil.sportsapp.viewmodel.HomeViewModel

class NewsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = getString(R.string.not_able_to_fetch_news_right_now_try_again_later)
    }
}