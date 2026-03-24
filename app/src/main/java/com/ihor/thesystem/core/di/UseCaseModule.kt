package com.ihor.thesystem.core.di

import com.ihor.thesystem.domain.repository.PlayerRepository
import com.ihor.thesystem.domain.repository.QuestRepository
import com.ihor.thesystem.domain.repository.ScheduleRepository
import com.ihor.thesystem.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides @Singleton
    fun provideGenerateDailyQuestsUseCase(
        playerRepo: PlayerRepository,
        questRepo: QuestRepository,
        scheduleRepo: ScheduleRepository
    ) = GenerateDailyQuestsUseCase(playerRepo, questRepo, scheduleRepo)

    @Provides @Singleton
    fun provideAdvanceCycleDayUseCase(
        playerRepo: PlayerRepository,
        questRepo: QuestRepository,
        generateQuests: GenerateDailyQuestsUseCase
    ) = AdvanceCycleDayUseCase(playerRepo, questRepo, generateQuests)

    @Provides @Singleton
    fun provideGetStatusScreenDataUseCase(
        playerRepo: PlayerRepository,
        questRepo: QuestRepository,
        debuffRepo: com.ihor.thesystem.domain.repository.DebuffRepository
    ) = GetStatusScreenDataUseCase(playerRepo, questRepo, debuffRepo)
}