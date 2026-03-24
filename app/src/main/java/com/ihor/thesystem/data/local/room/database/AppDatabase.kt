package com.ihor.thesystem.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ihor.thesystem.data.local.room.converters.Converters
import com.ihor.thesystem.data.local.room.dao.*
import com.ihor.thesystem.data.local.room.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    // Callback для початкового заповнення БД
    class PopulateCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        lateinit var database: AppDatabase

        override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
            super.onCreate(db)
            scope.launch(Dispatchers.IO) {
                DatabasePopulator.populate(
                    playerDao            = database.playerDao(),
                    systemConfigDao      = database.systemConfigDao(),
                    workoutDao           = database.workoutDao(),
                    scheduleDao          = database.scheduleDao(),
                    progressionMatrixDao = database.progressionMatrixDao(),
                    debuffConfigDao      = database.debuffConfigDao()
                )
            }
        }
    }
}