package com.ihor.thesystem.domain.usecase

import com.ihor.thesystem.domain.model.DomainQuestStatus
import com.ihor.thesystem.domain.repository.PlayerRepository
import com.ihor.thesystem.domain.repository.QuestRepository
import com.ihor.thesystem.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * Завершує поточний день і переходить до наступного дня циклу (1→2→3→4→1).
 * Позначає активні квести як COMPLETED або FAILED.
 */
class AdvanceCycleDayUseCase @Inject constructor(
    private val playerRepo: PlayerRepository,
    private val questRepo: QuestRepository,
    private val generateQuests: GenerateDailyQuestsUseCase
) {
    suspend operator fun invoke(forceComplete: Boolean = false) {
        val player = playerRepo.getPlayer().firstOrNull() ?: return

        // Завершуємо/провалюємо активні квести
        val daily = questRepo.getActiveDailyQuest().firstOrNull()
        val main  = questRepo.getActiveMainQuest().firstOrNull()

        daily?.let {
            val allDone = it.tasks.isNotEmpty() && it.tasks.all { t -> t.isCompleted }
            val status  = if (allDone || forceComplete) DomainQuestStatus.COMPLETED
            else DomainQuestStatus.FAILED
            questRepo.updateQuestStatus(it.id, status)
        }
        main?.let {
            val allDone = it.tasks.isNotEmpty() && it.tasks.all { t -> t.isCompleted }
            val status  = if (allDone || forceComplete) DomainQuestStatus.COMPLETED
            else DomainQuestStatus.FAILED
            questRepo.updateQuestStatus(it.id, status)
        }

        // Переходимо до наступного дня
        val nextDay = if (player.currentCycleDay >= 4) 1 else player.currentCycleDay + 1
        playerRepo.updatePlayer(player.copy(currentCycleDay = nextDay))

        // Генеруємо квести для нового дня
        generateQuests()
    }
}

/**
 * Повертає інформацію про всі 4 дні циклу для екрану редагування.
 */
class GetFullScheduleUseCase @Inject constructor(
    private val scheduleRepo: ScheduleRepository
) {
    operator fun invoke(day: Int) = scheduleRepo.getScheduleForDay(day)
}