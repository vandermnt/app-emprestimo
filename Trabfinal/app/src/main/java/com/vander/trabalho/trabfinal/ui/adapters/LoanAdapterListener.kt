package com.vander.trabalho.trabfinal.ui.adapters

import com.vander.trabalho.trabfinal.entities.Loan

interface LoanAdapterListener{
    fun viewLoan(loan: Loan)
    fun updateStatus(loan: Loan)
    fun viewHistoric(loan: Loan)
}