package com.diegocalero.laliga.di

import com.diegocalero.laliga.apis.TheSportDBApi
import com.diegocalero.laliga.apis.TheSportDBClient
import com.diegocalero.laliga.data.datasources.TeamsRemoteDataSource
import com.diegocalero.laliga.data.datasources.TeamsRemoteDataSourceImpl
import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.data.repositories.TeamsRepositoryImpl
import com.diegocalero.laliga.usecases.ListLastEventsByTeamId
import com.diegocalero.laliga.usecases.ListTeamsByLeagueName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideTheSportDBClient(): TheSportDBClient {
        return TheSportDBClient()
    }

    @Provides
    @Singleton
    fun provideTheSportDBApi(theSportDBClient: TheSportDBClient): TheSportDBApi {
        return theSportDBClient.getApi()
    }

    @Provides
    @Singleton
    fun provideTeamsRemoteDataSource(theSportDBApi: TheSportDBApi): TeamsRemoteDataSource {
        return TeamsRemoteDataSourceImpl(theSportDBApi)
    }

    @Provides
    @Singleton
    fun provideTeamsRepository(teamsRemoteDataSource: TeamsRemoteDataSource): TeamsRepository {
        return TeamsRepositoryImpl(teamsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideListLastEventsByTeamId(teamsRepository: TeamsRepository): ListLastEventsByTeamId {
        return ListLastEventsByTeamId(teamsRepository)
    }

    @Provides
    @Singleton
    fun provideListTeamsByLeagueName(teamsRepository: TeamsRepository): ListTeamsByLeagueName {
        return ListTeamsByLeagueName(teamsRepository)
    }
}