package com.example.semestralna_praca
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ExerciseDao {
    @Insert
    fun insertExercise(exercise : Exercise)

    @Query("SELECT * FROM exercises")
    fun getAllExecises(): List<Exercise>

    @Query("DELETE FROM exercises WHERE SUBSTR(name, 1, INSTR(name, ':') - 1) = :planName")
    fun deleteExercisesFromPlan(planName: String)
}