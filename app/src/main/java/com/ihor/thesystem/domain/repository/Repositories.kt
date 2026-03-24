package com.ihor.thesystem.domain.repository

import com.ihor.thesystem.domain.model.*
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun getPlayer(): Flow<Player?>
    fun getLatestWeight(): Flow<Float?>
    suspend fun updatePlayer(player: Player)
    suspend fun logWeight(weight: Float)
}

interface QuestRepository {
    fun getActiveDailyQuest(): Flow<Quest?>
    fun getActiveMainQuest(): Flow<Quest?>
    suspend fun hasActiveQuests(): Boolean
    suspend fun toggleTaskCompletion(taskId: Int, questId: Int, isCompleted: Boolean)
    suspend fun updateQuestStatus(questId: Int, status: DomainQuestStatus)
    suspend fun createDailyQuest(title: String, tasks: List<String>, scheduleId: Int?)
    suspend fun createMainQuest(title: String, exercises: List<String>, scheduleId: Int?)
}

interface SystemConfigRepository {
    fun getConfig(): Flow<SystemConfig?>
    suspend fun updateConfig(config: SystemConfig)
}

interface DebuffRepository {
    fun getAllDebuffs(): Flow<List<DebuffConfig>>
    fun getActiveDebuffs(): Flow<List<DebuffConfig>>
    suspend fun updateDebuff(debuff: DebuffConfig)
}

interface ScheduleRepository {
    fun getScheduleForDay(day: Int): Flow<ScheduleDay?>
}