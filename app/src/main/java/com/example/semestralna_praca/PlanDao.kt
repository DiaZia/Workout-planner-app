package com.example.semestralna_praca

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PlanDao {

    @Insert
    fun insertPlan(plan: Plan)

    @Query("SELECT * FROM plans")
    fun getAllPlans(): List<Plan>

    @Query("SELECT DISTINCT name FROM plans")
    fun getAllUniquePlans(): List<String>

    @Query("DELETE from plans where name = :planName")
    fun removePlan(planName : String);
}
