package com.vander.trabalho.trabfinal.ui

import androidx.fragment.app.Fragment
import com.vander.trabalho.trabfinal.AddPeopleActivity
import com.vander.trabalho.trabfinal.entities.People
import kotlinx.android.synthetic.main.fragment_add_people.*
import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import java.util.*
import android.util.Log
import com.vander.trabalho.trabfinal.MainActivity
import kotlinx.android.synthetic.main.activity_add_people.*
import android.content.Intent
import android.os.Bundle
import com.vander.trabalho.trabfinal.R
import com.vander.trabalho.trabfinal.entities.CEP
import com.vander.trabalho.trabfinal.datasource.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FragmentAddPeople : Fragment(){

    lateinit var addpeople: AddPeopleActivity
    lateinit var callback: T
    lateinit var datePicker: DatePickerDialog.OnDateSetListener
    lateinit var logradouroService:String
    lateinit var bairroService:String
    var msg:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_people, containerrr, false)
    }

    interface T{ fun save(people: People) }

    fun setOnHeadlineSelectedListener(callback: T, msg: Int) {
        this.callback = callback
        this.msg = msg
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try { addpeople = activity as AddPeopleActivity }
        catch (e: ClassCastException) {
            throw ClassCastException("$activity deve implementar CreateEmailListener")
        }

    }

    override fun onResume() {
        super.onResume()


        cep.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(cep.length()==8){
                    val call = RetrofitConfig().getCEPService().buscarCEP(cep.text.toString())
                    call.enqueue(object : Callback<CEP> {
                        override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
                            val cep = response.body()

                            logradouroService = cep!!.logradouro.toString()
                            bairroService     = cep!!.bairro.toString()

                            bairro.setText(bairroService)
                            endereco.setText(logradouroService)
                        }

                        override fun onFailure(call: Call<CEP>, t: Throwable) {
                            Log.e("CEPService   ", "Erro ao buscar o cep:" + t.message)
                        }
                    })
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })


        peopleFragment.setOnClickListener(){

            if(name.text.toString().equals("")) { this.name.setError("[Campo Vázio] Digite um nome") }
            else if (tel.text.toString().equals("")) { this.tel.setError("[Campo Vázio] Digite um telefone") }
            else if (cep.text.toString().equals("")) { this.cep.setError("[Campo Vázio] Digite um CEP") }
            else if (bairro.text.toString().equals("")) { this.bairro.setError("[Campo Vázio] Digite um bairro") }
            else if (endereco.text.toString().equals("")) { this.endereco.setError("[Campo Vázio] Digite um endereço") }
            else {
                var name: String     = this.name.text.toString()
                var tel: String      = this.tel.text.toString()
                var bairro: String   = this.bairro.text.toString()
                var endereco: String = this.endereco.text.toString()
                var datanasc: String = this.date_nasc.text.toString()
                var cep: String      = this.cep.text.toString()

                var people = People(name, datanasc, tel, cep, bairro, endereco)
                callback.save(people)

                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("teste", "teste")
                intent.putExtra("idItem", this.msg)

                startActivity(intent)
            }
        }

        date_nasc.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                addpeople,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                datePicker,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d("dwa","onDateSet: dd/mm/yyy: $month/$day/$year")

            val date = "$day/$month/$year"
            date_nasc.setText(date)
        }
    }
}

