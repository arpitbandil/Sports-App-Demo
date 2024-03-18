package com.arpitbandil.sportsapp.ui.team

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.ItemPlayersBinding
import com.arpitbandil.sportsapp.modal.Player
import com.arpitbandil.sportsapp.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale

class MiniPlayerAdapter(
    private val players: ArrayList<Player>,
    val onClickItem: (Int, Player, Map<View, String>) -> Unit
) :
    RecyclerView.Adapter<MiniPlayerAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder =
        PlayerViewHolder(p0.viewBinding(ItemPlayersBinding::inflate))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) = p0.bind(players[p1])

    inner class PlayerViewHolder(private val binding: ItemPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) = binding.let {
            it.ivCaptain.isVisible = bindingAdapterPosition == 0
            Glide.with(it.ivPlayer).load(player.image).apply(RequestOptions().dontTransform())
                .into(it.ivPlayer)
            it.ivFlag.setImageResource(FlagKit.getResId(itemView.context, player.flagCode))
            it.tvCountryName.text = Locale("", player.flagCode.uppercase()).displayCountry
            it.tvName.text = player.name
            it.tvRole.text = player.role
            binding.ivPlayer.transitionName =
                itemView.context.getString(R.string.player_icon_transition, bindingAdapterPosition)
            it.cnsItemPlayer.setOnClickListener {
                onClickItem.invoke(
                    bindingAdapterPosition,
                    player,
                    mapOf(binding.ivPlayer to binding.ivPlayer.transitionName)
                )
            }
            it.executePendingBindings()
        }
    }
}
