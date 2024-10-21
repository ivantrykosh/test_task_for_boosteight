package com.ivantrykosh.app.test_task_for_boosteight.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Heart rate entity
 * @param id id of heart rate in db
 * @param heartRate heart rate in bpm
 * @param dateTime time in ms from the Epoch
 */
@Entity(tableName = "heart_rate")
data class HeartRate(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val heartRate: Int,
    val dateTime: Long,
)