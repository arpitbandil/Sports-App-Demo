package com.arpitbandil.sportsapp

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet


fun getTransition() = TransitionSet().apply {
    setOrdering(TransitionSet.ORDERING_TOGETHER)
    addTransition(ChangeImageTransform())
    addTransition(ChangeBounds())
    addTransition(ChangeTransform())
}.setDuration(500)