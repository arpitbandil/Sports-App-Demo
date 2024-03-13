package com.arpitbandil.sportsapp.ui.rating

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.ItemTeamRankBinding
import com.arpitbandil.sportsapp.modal.Team
import com.arpitbandil.sportsapp.viewBinding
import com.bumptech.glide.Glide
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale

class MiniTeamAdapter() :
    PagingDataAdapter<Team, MiniTeamAdapter.TeamViewHolder>(TeamListDiffCallback()) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder =
        TeamViewHolder(p0.viewBinding(ItemTeamRankBinding::inflate))

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) = p0.bind(getItem(p1))

    inner class TeamViewHolder(val binding: ItemTeamRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team?) = binding.let {
            if (team == null) return@let
            it.ivArrow.isVisible = team.isRankDisplay
            it.ivArrow.setImageResource(if (team.isRankUp) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow)
            Glide.with(it.ivTeamIcon).load(team.icon).into(it.ivTeamIcon)
            it.ivFlag.setImageResource(FlagKit.getResId(itemView.context, team.flagCode))
            it.tvCountryName.text = Locale("", team.flagCode.uppercase()).displayCountry
            it.tvNetWorth.text = team.netWorth
            it.tvTeamName.text = team.name
            it.tvRank.text = team.rank.toString()
            it.tvSrNo.text = adapterPosition.inc().toString()
        }
    }

    class TeamListDiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }

}
