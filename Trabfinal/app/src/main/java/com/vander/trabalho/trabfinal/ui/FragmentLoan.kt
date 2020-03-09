package com.vander.trabalho.trabfinal.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.AddLoanActivity
import com.vander.trabalho.trabfinal.MainActivity
import com.vander.trabalho.trabfinal.db.dao.LoanDao
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.ui.adapters.LoanAdapter
import com.vander.trabalho.trabfinal.ui.adapters.LoanAdapterListener
import kotlinx.android.synthetic.main.fragment_loan.*

class FragmentLoan : Fragment(), LoanAdapterListener {

    lateinit var loanDAO: LoanDao
    lateinit var adapter: LoanAdapter
    lateinit var loadloan: MainActivity
    lateinit var callback: FragmentLoan.LoadLoan
    lateinit var appContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadloan = activity as MainActivity
        return inflater.inflate(com.vander.trabalho.trabfinal.R.layout.fragment_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadloan.onAttachFragment(this)
        loadLoann()
    }

    fun setOnHeadlineSelectedListener(
        callback: LoadLoan,
        loanDAO: LoanDao,
        applicationContext: Context
    ) {
        this.loanDAO = loanDAO
        this.callback = callback
        this.appContext = applicationContext
    }

    interface LoadLoan { fun loadLoan() }

    override fun onResume() {
        super.onResume()

            addLoan.setOnClickListener(){
                val intent = Intent(activity, AddLoanActivity::class.java)
                startActivity(intent)
            }
    }


    fun loadLoann() {
        val loan = loanDAO.getAll("Emprestado", "Atrasado")
        val flag = true

        adapter = LoanAdapter(loan.toMutableList(), flag, this)

        listLoan.adapter = adapter

        listLoan.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
    }

    override fun updateStatus(loan: Loan) {
        loanDAO.updateStatus(loan)
        Toast.makeText(appContext, "Empreśtimo Recebido!", Toast.LENGTH_SHORT).show()
        loadLoann()
    }

    override fun viewLoan(loan: Loan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun viewHistoric(loan: Loan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
