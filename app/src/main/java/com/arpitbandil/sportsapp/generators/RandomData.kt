package com.arpitbandil.sportsapp.generators

import androidx.paging.PagingData
import com.arpitbandil.sportsapp.modal.Team
import com.murgupluoglu.flagkit.FlagKit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

val teamNames = listOf(
    "Conquerors",
    "Innovators",
    "Allies",
    "Bandits",
    "Brainiacs",
    "Chosen Ones",
    "Desk champions",
    "Firefly Squad",
    "Incognito mode",
    "Performers",
    "Power Players",
    "The Deciders",
    "The Wicked crew",
    "Toon Squad",
    "Untouchables",
    "Virtuosos",
    "Wizards",
    "Wolf Pack",
    "Your Worst Nightmare"
)

val teamIcons = listOf(
    "https://static.vecteezy.com/system/resources/thumbnails/010/884/730/small_2x/owl-head-mascot-team-logo-png.png",
    "https://i.pinimg.com/originals/65/07/b0/6507b06cceca9107713f76c779c814d9.png",
    "https://static.vecteezy.com/system/resources/thumbnails/016/467/720/small/tiger-logo-esport-team-png.png",
    "https://seeklogo.com/images/M/mexico-national-football-team-logo-DD4918797B-seeklogo.com.png",
    "https://freepngimg.com/thumb/team/91409-gray-head-sports-wolf-team-logo-sport-thumb.png",
    "https://seeklogo.com/images/P/portugal-national-football-team-logo-745E6D1B4D-seeklogo.com.png",
    "https://99designs-blog.imgix.net/blog/wp-content/uploads/2020/07/1200px-Detroit_Red_Wings_logo.svg.png",
    "https://seeklogo.com/images/N/netherlands-football-team-logo-99AD54AC2D-seeklogo.com.png",
    "https://a.espncdn.com/i/teamlogos/nfl/500/ten.png",
    "https://companieslogo.com/img/orig/2020.HK-97bd150d.png",
    "https://brandlogos.net/wp-content/uploads/2014/12/tampa_bay_buccaneers-logo_brandlogos.net_mctz4.png",
    "https://s.yimg.com/cv/apiv2/default/nfl/20190724/500x500/2019_PHI.png"
//    "https://cdn.freebiesupply.com/images/thumbs/1x/arizona-cardinals-logo.png",
)

val flagCodes = FlagKit.getAllAvailableCodes()

object Generator {
    fun generateTeams(count: Int) =  arrayListOf<Team>().apply {
        for (i in 1..count) {
            add(
                Team(
                    teamNames.random(),
                    teamIcons.random(),
                    Random.nextInt(1, 1000),
                    "$${Random.nextInt(1, 999)}${listOf("M", "K").random()}",
                    Random.nextBoolean(),
                    Random.nextBoolean(),
                    flagCodes.random()
                )
            )
        }
    }
}
