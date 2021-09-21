package com.diegocalero.laliga.data.dtos

import com.squareup.moshi.Json

data class ListTeamsDTO (

	@field:Json(name = "teams") val teams : List<TeamDTO>
)