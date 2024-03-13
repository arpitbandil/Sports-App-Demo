package com.arpitbandil.sportsapp.ui.team

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arpitbandil.sportsapp.databinding.ItemGameChipBinding
import com.arpitbandil.sportsapp.viewBinding
import kotlin.math.min

class GameChipAdapter(private val games: List<String>) :
    RecyclerView.Adapter<GameChipAdapter.GameChipViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GameChipViewHolder =
        GameChipViewHolder(p0.viewBinding(ItemGameChipBinding::inflate))

    override fun onBindViewHolder(p0: GameChipViewHolder, p1: Int) = p0.bind(games[p1])

    inner class GameChipViewHolder(private val binding: ItemGameChipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gameName: String) = binding.let {
            it.tvName.text =
                if (bindingAdapterPosition < 3) gameName else "+".plus(games.size - bindingAdapterPosition)
            it.executePendingBindings()
        }
    }

    override fun getItemCount() = min(games.size, 4)
}
