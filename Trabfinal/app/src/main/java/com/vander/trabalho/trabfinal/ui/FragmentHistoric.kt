package com.vander.trabalho.trabfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.MainActivity
import com.vander.trabalho.trabfinal.R
import com.vander.trabalho.trabfinal.db.dao.LoanDao
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.ui.adapters.LoanAdapter
import com.vander.trabalho.trabfinal.ui.adapters.LoanAdapterListener
import kotlinx.android.synthetic.main.fragment_historic.*

class FragmentHistoric : Fragment(), LoanAdapterListener {

    lateinit var loanDAO: LoanDao
    lateinit var adapter: LoanAdapter
    lateinit var loadloan: MainActivity
    lateinit var callback: FragmentLoan.LoadLoan

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadloan = activity as MainActivity
        return inflater.inflate(R.layout.fragment_historic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadloan.onAttachFragment(this)
        loadLoann()
    }

    fun setOnHeadlineSelectedListener(callback: FragmentLoan.LoadLoan, loanDAO: LoanDao) {
        this.loanDAO = loanDAO
        this.callback = callback
    }

    interface LoadLoan {
        fun loadLoan()
    }

    fun loadLoann() {
        val loan = loanDAO.getReceived("Recebido")
        val flag = false

        adapter = LoanAdapter(loan.toMutableList(), flag, this)

        listHistoricLoan.adapter = adapter

        listHistoricLoan.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
    }

    override fun viewHistoric(loan: Loan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateStatus(loan: Loan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun viewLoan(loan: Loan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
