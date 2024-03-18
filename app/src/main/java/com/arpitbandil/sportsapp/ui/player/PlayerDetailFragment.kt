package com.arpitbandil.sportsapp.ui.player

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
import com.arpitbandil.sportsapp.databinding.FragmentPlayerDetailsBinding
import com.arpitbandil.sportsapp.generators.Generator.getRoles
import com.arpitbandil.sportsapp.generators.teamIcons
import com.arpitbandil.sportsapp.getTransition
import com.arpitbandil.sportsapp.ui.rating.RoleAdapter
import com.arpitbandil.sportsapp.ui.team.TeamResultFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayoutMediator

class PlayerDetailFragment : Fragment() {

    private val args: PlayerDetailFragmentArgs by navArgs()
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
        handleTransition()
        setUpUI()
    }

    private fun handleTransition() {
        postponeEnterTransition()
        sharedElementEnterTransition = getTransition()
        binding.ivPlayer.transitionName = getString(R.string.player_icon_transition, args.position)
        loadPlayerImage()
    }

    private fun loadPlayerImage() = binding.ivPlayer.apply {
        Glide.with(this).load(args.player.image).apply(
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
        Glide.with(ivIcon).load(teamIcons.random()).into(ivIcon)
        tvName.text = args.player.name
        ivBack.setOnClickListener { findNavController().navigateUp() }
        rvPositions.adapter = RoleAdapter(getRoles())
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
                0 -> PlayerOverviewFragment.newInstance(args.player)
                1 -> TeamResultFragment()
                else -> PlayerSocialFragment()
            }
        }

    }
}