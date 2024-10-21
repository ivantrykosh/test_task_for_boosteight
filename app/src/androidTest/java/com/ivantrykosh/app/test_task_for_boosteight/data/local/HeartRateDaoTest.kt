package com.ivantrykosh.app.test_task_for_boosteight.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeartRateDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var heartRateDao: HeartRateDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        heartRateDao = db.heartRateDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertHeartRateAndGetInList() = runTest {
        val heartRate = HeartRate(heartRate = 80, dateTime = 17755533)
        heartRateDao.insertHeartRate(heartRate)
        val heartRatesInDb = heartRateDao.getAllHeartRates()
        assertEquals(heartRate.heartRate, heartRatesInDb[0].heartRate)
        assertEquals(heartRate.dateTime, heartRatesInDb[0].dateTime)
    }

    @Test
    fun insertHeartRatesAndGetInList() = runTest {
        val heartRates = listOf(HeartRate(heartRate = 80, dateTime = 17755533), HeartRate(heartRate = 100, dateTime = 17757533), HeartRate(heartRate = 90, dateTime = 17795533))
        heartRates.forEach { heartRateDao.insertHeartRate(it) }
        val heartRatesInDb = heartRateDao.getAllHeartRates()
        assertEquals(heartRates.size, heartRatesInDb.size)
        for (i in heartRates.indices) {
            assertEquals(heartRates[i].heartRate, heartRatesInDb[i].heartRate)
            assertEquals(heartRates[i].dateTime, heartRatesInDb[i].dateTime)
        }
    }

    @Test
    fun insertHeartRateAndDeleteAndGetInList() = runTest {
        val heartRate = HeartRate(heartRate = 80, dateTime = 17755533)
        heartRateDao.insertHeartRate(heartRate)
        var heartRatesInDb = heartRateDao.getAllHeartRates()
        assertEquals(heartRate.heartRate, heartRatesInDb[0].heartRate)
        assertEquals(heartRate.dateTime, heartRatesInDb[0].dateTime)

        heartRateDao.deleteAllHeartRates()
        heartRatesInDb = heartRateDao.getAllHeartRates()
        assertEquals(0, heartRatesInDb.size)
    }
}