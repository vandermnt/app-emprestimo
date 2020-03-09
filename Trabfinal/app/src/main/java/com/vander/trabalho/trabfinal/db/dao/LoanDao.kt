package com.vander.trabalho.trabfinal.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vander.trabalho.trabfinal.entities.Loan

@Dao
interface LoanDao{
    @Query("SELECT * FROM loan WHERE status = :borrowed or status = :late")
    fun getAll(borrowed:String, late:String): List<Loan>

    @Query("SELECT * FROM loan WHERE status = :status")
    fun getReceived(status: String): List<Loan>

    @Insert
    fun insert(loan: Loan): Long

    @Update
    fun updateStatus(loan: Loan)
}