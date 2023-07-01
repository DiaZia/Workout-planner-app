package com.example.semestralna_praca

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey

@Entity(tableName = "plans",
        foreignKeys = [ForeignKey(
    entity = Exercise::class,
    parentColumns = ["name"],
    childColumns = ["exercise"],
    onDelete = ForeignKey.CASCADE)]
)
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo (name = "name") val name: String,
    @ColumnInfo(name = "exercise") val exercise: String,
    @ColumnInfo(name = "date") val date: String,
)