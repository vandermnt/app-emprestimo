package com.vander.trabalho.trabfinal.datasource

import android.content.Context
import androidx.room.Room
import com.vander.trabalho.trabfinal.db.AppDatabase
import com.vander.trabalho.trabfinal.db.dao.LoanDao
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.entities.People

class LoanLocalDataSource(applicationContext: Context) {

    var peopleDao: PeopleDao
    var loanDao: LoanDao

    init {
        val db =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "loan.db"
            )
                .allowMainThreadQueries()
                .build()
        peopleDao = db.peopleDao()
        loanDao = db.loanDao()

    }

    fun savePeople(people: People){
        peopleDao.insert(people)
    }

    fun saveLoan(loan: Loan){
        loanDao.insert(loan)
    }
}