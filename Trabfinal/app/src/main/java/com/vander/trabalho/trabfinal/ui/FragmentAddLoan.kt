package com.vander.trabalho.trabfinal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vander.trabalho.trabfinal.AddPeopleActivity
import com.vander.trabalho.trabfinal.R
import kotlinx.android.synthetic.main.fragment_add_loan.*

class FragmentAddLoan : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_loan, container, false)
    }

    override fun onResume() {
        super.onResume()

        btYes.setOnClickListener(){
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentSearchPeople = FragmentSearchPeople()

            fragmentManager.beginTransaction().replace(R.id.containerr, fragmentSearchPeople).commit()
        }

        btNo.setOnClickListener(){
            val intent = Intent(activity, AddPeopleActivity::class.java)
            intent.putExtra("addpeople_loan", "addloan_loan")
            startActivity(intent)
        }
    }
}
