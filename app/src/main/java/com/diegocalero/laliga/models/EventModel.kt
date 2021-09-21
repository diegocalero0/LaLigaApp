package com.diegocalero.laliga.models

data class EventModel(
    val idEvent : Int?,
    val strEvent : String?,
    val strLeague : String?,
    val strSeason : String?,
    val strHomeTeam : String?,
    val strAwayTeam : String?,
    val strTimestamp : String?,
    val intHomeScore : Int?,
    val intAwayScore : Int?,
    val strThumb : String?,
    val dateEvent : String?,
    val strTime : String?
)