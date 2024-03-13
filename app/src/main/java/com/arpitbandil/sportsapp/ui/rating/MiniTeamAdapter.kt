package com.arpitbandil.sportsapp.ui.rating

import android.view.View
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
import com.bumptech.glide.request.RequestOptions
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale

class MiniTeamAdapter(val onClickItem: (Int, Team, Map<View, String>) -> Unit) :
    PagingDataAdapter<Team, MiniTeamAdapter.TeamViewHolder>(TeamListDiffCallback()) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder =
        TeamViewHolder(p0.viewBinding(ItemTeamRankBinding::inflate))

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) = p0.bind(getItem(p1))

    inner class TeamViewHolder(private val binding: ItemTeamRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team?) = binding.let {
            if (team == null) return@let
            it.ivArrow.isVisible = team.isRankDisplay
            it.ivArrow.setImageResource(if (team.isRankUp) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow)
            Glide.with(it.ivTeamIcon).load(team.icon).apply(RequestOptions().dontTransform())
                .into(it.ivTeamIcon)
            it.ivFlag.setImageResource(FlagKit.getResId(itemView.context, team.flagCode))
            it.tvCountryName.text = Locale("", team.flagCode.uppercase()).displayCountry
            it.tvNetWorth.text = team.netWorth
            it.tvTeamName.text = team.name
            it.tvRank.text = team.rank.toString()
            it.tvSrNo.text = bindingAdapterPosition.inc().toString()
            binding.ivTeamIcon.transitionName =
                itemView.context.getString(R.string.team_icon_transition, bindingAdapterPosition)
            binding.tvTeamName.transitionName =
                itemView.context.getString(R.string.team_name_transition, bindingAdapterPosition)
            it.cnsItemTeam.setOnClickListener {
                onClickItem.invoke(
                    bindingAdapterPosition,
                    team,
                    mapOf(
                        binding.ivTeamIcon to binding.ivTeamIcon.transitionName,
                        binding.tvTeamName to binding.tvTeamName.transitionName,
                    )
                )
            }
            it.executePendingBindings()
        }
    }

    class TeamListDiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }

}
