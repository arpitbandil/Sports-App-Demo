package com.arpitbandil.sportsapp.ui.team

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.FragmentTeamDetailsBinding
import com.arpitbandil.sportsapp.generators.Generator.getGames
import com.arpitbandil.sportsapp.getTransition
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayoutMediator

class TeamDetailsFragment : Fragment() {

    private val args: TeamDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentTeamDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTeamDetailsBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleTransition()
        setUpUI()
    }

    private fun handleTransition() {
        postponeEnterTransition()
        sharedElementEnterTransition = getTransition()
        binding.ivTeamIcon.transitionName = getString(R.string.team_icon_transition, args.position)
        binding.tvName.transitionName = getString(R.string.team_name_transition, args.position)
        loadTeamImage()
        binding.tvName.text = args.team.name
    }

    private fun loadTeamImage() = binding.ivTeamIcon.apply {
        Glide.with(this).load(args.team.icon).apply(
            RequestOptions().dontTransform().diskCacheStrategy(
                DiskCacheStrategy.RESOURCE
            )
        ).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                p0: GlideException?,
                p1: Any?,
                p2: Target<Drawable>,
                p3: Boolean
            ) = false

            override fun onResourceReady(
                p0: Drawable,
                p1: Any,
                p2: Target<Drawable>?,
                p3: DataSource,
                p4: Boolean
            ): Boolean {
                startPostponedEnterTransition()
                return false
            }
        })
            .into(this)
    }

    private fun setUpUI() = binding.apply {
        ivBack.setOnClickListener { findNavController().navigateUp() }
        rvGames.adapter = GameChipAdapter(getGames())
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
                0 -> OverviewFragment()
                1 -> TeamResultFragment(args.team.name)
                else -> TeamMatchesFragment()
            }
        }

    }
}