package com.vander.trabalho.trabfinal.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vander.trabalho.trabfinal.entities.People

@Dao
interface PeopleDao{
    @Query("SELECT * FROM people ORDER BY name")
    fun getAll(): List<People>

    @Insert
    fun insert(people: People): Long
}