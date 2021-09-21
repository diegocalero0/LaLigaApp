package com.diegocalero.laliga.apis

import com.diegocalero.laliga.data.dtos.ListEventsDTO
import com.diegocalero.laliga.data.dtos.ListTeamsDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class TheSportDBClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thesportsdb.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun getApi(): TheSportDBApi{
        return retrofit.create(TheSportDBApi::class.java)
    }

}

interface TheSportDBApi {

    @GET("/api/v1/json/{apiKey}/search_all_teams.php")
    fun listAllTeams(@Path("apiKey") apiKey: String, @Query("l") leagueName: String): Call<ListTeamsDTO>

    @GET("/api/v1/json/{apiKey}/eventslast.php")
    fun listLastEvents(@Path("apiKey") apiKey: String, @Query("id") teamId: String?): Call<ListEventsDTO>
}