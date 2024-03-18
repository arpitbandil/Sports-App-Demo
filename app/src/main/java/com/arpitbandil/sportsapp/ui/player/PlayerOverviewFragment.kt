package com.arpitbandil.sportsapp.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arpitbandil.sportsapp.databinding.FragmentPlayerOverviewBinding
import com.arpitbandil.sportsapp.generators.Generator.getBirthday
import com.arpitbandil.sportsapp.generators.Generator.getRandomQuote
import com.arpitbandil.sportsapp.modal.Player
import com.murgupluoglu.flagkit.FlagKit
import java.util.Locale

class PlayerOverviewFragment : Fragment() {

    private lateinit var binding: FragmentPlayerOverviewBinding
    private var player: Player? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlayerOverviewBinding.inflate(inflater).apply {
        arguments?.getParcelable<Player>(ARG_PLAYER)?.let { player = it }
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = binding.apply {
        if (player == null) return@apply
        tvBirthValue.text = getBirthday()
        tvCountryValue.text = Locale("", player!!.flagCode.uppercase()).displayCountry
        tvNameValue.text = player!!.name
        ivFlag.setImageResource(FlagKit.getResId(requireContext(), player!!.flagCode))
        tvBioValue.text = getRandomQuote()
    }


    companion object {
        const val ARG_PLAYER = "player"
        fun newInstance(player: Player) = PlayerOverviewFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PLAYER, player)
            }
        }
    }
}