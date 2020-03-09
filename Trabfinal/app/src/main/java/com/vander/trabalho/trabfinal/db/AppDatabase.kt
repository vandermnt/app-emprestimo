package com.vander.trabalho.trabfinal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vander.trabalho.trabfinal.db.dao.LoanDao
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.entities.People

@Database(entities = arrayOf(Loan::class, People::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun loanDao(): LoanDao
    abstract fun peopleDao(): PeopleDao
}