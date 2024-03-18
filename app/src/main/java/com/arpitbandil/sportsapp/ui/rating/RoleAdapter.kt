package com.arpitbandil.sportsapp.ui.rating

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arpitbandil.sportsapp.databinding.ItemRoleBinding
import com.arpitbandil.sportsapp.viewBinding
import kotlin.math.min

class RoleAdapter(private val games: List<String>) :
    RecyclerView.Adapter<RoleAdapter.RoleViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RoleViewHolder =
        RoleViewHolder(p0.viewBinding(ItemRoleBinding::inflate))

    override fun onBindViewHolder(p0: RoleViewHolder, p1: Int) = p0.bind(games[p1])

    inner class RoleViewHolder(private val binding: ItemRoleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(role: String) = binding.let {
            it.tvName.text = role
            it.executePendingBindings()
        }
    }

    override fun getItemCount() = min(games.size, 4)
}
