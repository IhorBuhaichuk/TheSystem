package com.ihor.thesystem.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ihor.thesystem.data.local.room.converters.Converters
import com.ihor.thesystem.data.local.room.dao.*
import com.ihor.thesystem.data.local.room.entity.*

@Database(
    entities = [
        PlayerEntity::class,
        WeightLogEntity::class,
        SystemConfigEntity::class,
        ExerciseEntity::class,
        DailyTaskTemplateEntity::class,
        WorkoutTemplateEntity::class,
        WorkoutExerciseCrossRef::class,
        ScheduleEntity::class,
        ScheduleTaskCrossRef::class,
        QuestEntity::class,
        QuestTaskEntity::class,
        ProgressionMatrixEntity::class,
        DebuffConfigEntity::class,
        QuestLogEntity::class
        // ВАЖЛИВО: Якщо ти вже створював нові таблиці для ШІ,
        // просто прибери подвійний слеш (//) перед їхніми назвами нижче:
        // , WorkoutSessionLogEntity::class
        // , ExerciseSetLogEntity::class
        // , ExerciseMilestoneEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun weightLogDao(): WeightLogDao
    abstract fun systemConfigDao(): SystemConfigDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun questDao(): QuestDao
    abstract fun progressionMatrixDao(): ProgressionMatrixDao
    abstract fun debuffConfigDao(): DebuffConfigDao
    abstract fun questLogDao(): QuestLogDao

    // ВАЖЛИВО: Якщо ти вже створював нові DAO для ШІ,
    // прибери подвійний слеш (//) перед ними:
    // abstract fun workoutSessionLogDao(): WorkoutSessionLogDao
    // abstract fun exerciseSetLogDao(): ExerciseSetLogDao
    // abstract fun exerciseMilestoneDao(): ExerciseMilestoneDao
}