package com.vander.trabalho.trabfinal.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vander.trabalho.trabfinal.MainActivity
import com.vander.trabalho.trabfinal.R
import com.vander.trabalho.trabfinal.entities.Loan
import kotlinx.android.synthetic.main.fragment_confirmation_loan.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentConfirmationLoan : Fragment() {

    lateinit var callback: ConfirmationLoan
    lateinit var id_people: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments
        if (args != null) {

            tvNamePeople.text   = args.getString("name_people")
            tvDataNasc.text     = args.getString("data_nasc")
            tvTel.text          = args.getString("tel")
            tvCep.text          = args.getString("cep")
            tvBairro.text       = args.getString("bairro")
            tvEndereco.text     = args.getString("endereco")
            this.id_people      = args.getString("id_people")
        }

    }

    interface ConfirmationLoan{ fun saveLoan(loan: Loan) }

    fun setOnHeadlineSelectedListener(callback: ConfirmationLoan) { this.callback = callback }

    override fun onResume() {
        super.onResume()

        btConfirmationLoan.setOnClickListener{
            if(name_item.text.toString().equals("")) {
                this.name_item.error = "[Campo Vázio] Digite um nome"
            }
            else if (data_devolution.text.equals("")) {
                data_devolution.error = "[Campo Vázio] Digite uma data"
            }
            else{
                val name_item = name_item.text.toString()
                val data_devolution = data_devolution.text.toString()

                val dataHoraAtual = Date()
                val data = SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual)

                var loan = Loan(name_item, data, data_devolution, status = "Emprestado", id_people = this.id_people)
                callback.saveLoan(loan)

                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("addloan", "addloan")
                startActivity(intent)

            }

        }
    }
}
