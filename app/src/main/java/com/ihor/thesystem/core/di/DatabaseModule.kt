package com.ihor.thesystem.core.di

import android.content.Context
import androidx.room.Room
import com.ihor.thesystem.data.local.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): AppDatabase {
        lateinit var db: AppDatabase
        val callback = AppDatabase.PopulateCallback(
            scope            = scope,
            databaseProvider = { db }
        )
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "the_system_db"
        )
            .addCallback(callback)
            .fallbackToDestructiveMigration()
            .build()
        return db
    }

    @Provides fun providePlayerDao(db: AppDatabase)            = db.playerDao()
    @Provides fun provideWeightLogDao(db: AppDatabase)         = db.weightLogDao()
    @Provides fun provideSystemConfigDao(db: AppDatabase)      = db.systemConfigDao()
    @Provides fun provideWorkoutDao(db: AppDatabase)           = db.workoutDao()
    @Provides fun provideScheduleDao(db: AppDatabase)          = db.scheduleDao()
    @Provides fun provideQuestDao(db: AppDatabase)             = db.questDao()
    @Provides fun provideProgressionMatrixDao(db: AppDatabase) = db.progressionMatrixDao()
    @Provides fun provideDebuffConfigDao(db: AppDatabase)      = db.debuffConfigDao()
    @Provides fun provideQuestLogDao(db: AppDatabase)          = db.questLogDao()
}