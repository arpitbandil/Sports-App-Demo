package com.arpitbandil.sportsapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentHomeBinding
import com.arpitbandil.sportsapp.viewBinding
import com.arpitbandil.sportsapp.viewmodel.HomeViewModel

class ProfileFragment : Fragment(R.layout.fragment_home) {
    companion object {
        fun newInstance() = MatchesFragment()
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.setText("Profile")
    }

}