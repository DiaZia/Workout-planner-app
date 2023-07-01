package com.example.semestralna_praca
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "exercises")
data class Exercise (
    @PrimaryKey val name: String,
    @ColumnInfo(name = "imageId") val imageId: Int,
    @ColumnInfo(name = "property") val property: String,
)