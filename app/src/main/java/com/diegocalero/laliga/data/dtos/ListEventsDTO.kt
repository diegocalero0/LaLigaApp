package com.diegocalero.laliga.data.dtos

import com.squareup.moshi.Json

data class ListEventsDTO (

	@field:Json(name = "results") val results : List<EventDTO>
)