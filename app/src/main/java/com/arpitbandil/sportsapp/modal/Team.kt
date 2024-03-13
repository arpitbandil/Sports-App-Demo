package com.arpitbandil.sportsapp.modal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val name: String,
    val icon: String,
    val rank: Int,
    val netWorth: String,
    val isRankDisplay: Boolean,
    val isRankUp: Boolean,
    val flagCode: String,
) : Parcelable