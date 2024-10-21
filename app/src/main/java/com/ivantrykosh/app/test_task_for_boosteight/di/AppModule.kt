package com.ivantrykosh.app.test_task_for_boosteight.di

import android.content.Context
import androidx.room.Room
import com.ivantrykosh.app.test_task_for_boosteight.data.local.HeartRateDao
import com.ivantrykosh.app.test_task_for_boosteight.data.repository.HeartRateRepositoryImpl
import com.ivantrykosh.app.test_task_for_boosteight.domain.repository.HeartRateRepository
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Object for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provide singleton object for AppDatabase
     * @param context context of application
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "heart_rate.db"
        ).build()
    }

    /**
     * Provide singleton object for HeartRateDao
     * @param database app database
     */
    @Provides
    @Singleton
    fun provideHeartRateDao(database: AppDatabase): HeartRateDao {
        return database.heartRateDao()
    }

    /**
     * Provide singleton for HeartRateRepository
     * @param heartRateDao dao for HeartRate
     */
    @Provides
    @Singleton
    fun provideHeartRateRepository(heartRateDao: HeartRateDao): HeartRateRepository {
        return HeartRateRepositoryImpl(heartRateDao)
    }
}