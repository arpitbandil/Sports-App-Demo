package com.arpitbandil.sportsapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentPlayerDetailsBinding
import com.arpitbandil.sportsapp.ui.rating.RoleAdapter
import com.arpitbandil.sportsapp.ui.team.TeamResultFragment
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentPlayerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlayerDetailsBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = binding.apply {
        ivBack.isVisible = false
        ivIcon.setImageResource(R.drawable.aries)
        ivPlayer.setImageResource(R.drawable.arpit)
        tvName.text = getString(R.string.arpit_bandil)
        rvPositions.adapter =
            RoleAdapter(listOf("Developer", "Hard Working", "Intelligent", "Friendly"))
        val tabsList =
            mutableListOf<String>().apply { repeat(tabLayout.tabCount) { add(tabLayout.getTabAt(it)?.text.toString()) } }
        viewPager.adapter = FragmentAdapter(tabLayout.tabCount)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsList[position]
        }.attach()
        executePendingBindings()
    }

    inner class FragmentAdapter(private val tabCount: Int) :
        FragmentStateAdapter(requireActivity()) {
        override fun getItemCount() = tabCount

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProfileOverviewFragment()
                1 -> TeamResultFragment()
                else -> ProfileSocialFragment()
            }
        }
    }
}