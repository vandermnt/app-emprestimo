package com.vander.trabalho.trabfinal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vander.trabalho.trabfinal.datasource.LoanLocalDataSource
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.ui.*

class AddLoanActivity : AppCompatActivity(), FragmentSearchPeople.LoadPeople, FragmentConfirmationLoan.ConfirmationLoan{

    lateinit var db:LoanLocalDataSource
    lateinit var peopleDAO:PeopleDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_loan)

        val fragmentManager = supportFragmentManager
        val fragmente:FragmentAddLoan = FragmentAddLoan()

        fragmentManager.beginTransaction().replace(R.id.containerr, fragmente).commit()

        load()
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is FragmentSearchPeople) { fragment.setOnHeadlineSelectedListener(peopleDAO) }
        else if (fragment is FragmentConfirmationLoan){ fragment.setOnHeadlineSelectedListener(this)

        }
    }

    fun load(){
        db = LoanLocalDataSource(applicationContext)
        peopleDAO = db.peopleDao
    }

    override fun saveLoan(loan: Loan) {
        db = LoanLocalDataSource(applicationContext)
        db.saveLoan(loan)
        Log.d("TESTE:", "cadastrado com sucesso!!!!!")
    }

}
