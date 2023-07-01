package com.example.semestralna_praca

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Plan::class, Exercise::class], version = 5)
abstract class PlansDatabase : RoomDatabase() {

    abstract fun planDao(): PlanDao

    abstract fun exerciseDao(): ExerciseDao

}