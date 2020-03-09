package com.vander.trabalho.trabfinal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vander.trabalho.trabfinal.datasource.LoanLocalDataSource
import com.vander.trabalho.trabfinal.entities.People
import com.vander.trabalho.trabfinal.ui.FragmentAddPeople


class AddPeopleActivity : AppCompatActivity(), FragmentAddPeople.T{

    lateinit var db: LoanLocalDataSource
     var msg:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)



    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is FragmentAddPeople) {
            val it = intent
            msg = it.getIntExtra("idItem", 5)
            fragment.setOnHeadlineSelectedListener(this, this.msg)
        }
    }

    override fun save(people: People) {
        db = LoanLocalDataSource(applicationContext)
        db.savePeople(people)
    }

}
