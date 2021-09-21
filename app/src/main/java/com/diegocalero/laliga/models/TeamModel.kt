package com.diegocalero.laliga.models

import java.io.Serializable

data class TeamModel(
    val idTeam : String?,
    val strTeam : String?,
    val intFormedYear : Int?,
    val strStadium : String?,
    val strStadiumThumb : String?,
    val strStadiumDescription : String?,
    val strStadiumLocation : String?,
    val intStadiumCapacity : Int?,
    val strWebsite : String?,
    val strFacebook : String?,
    val strTwitter : String?,
    val strInstagram : String?,
    val strDescriptionEN : String?,
    val strDescriptionDE : String?,
    val strDescriptionFR : String?,
    val strDescriptionCN : String?,
    val strDescriptionIT : String?,
    val strDescriptionJP : String?,
    val strDescriptionRU : String?,
    val strDescriptionES : String?,
    val strTeamBadge : String?,
    val strTeamJersey : String?
): Serializable