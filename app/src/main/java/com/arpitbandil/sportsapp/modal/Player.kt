package com.arpitbandil.sportsapp.modal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val image: String,
    val role: String,
    val flagCode: String,
) : Parcelable