package com.vander.trabalho.trabfinal

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.vander.trabalho.trabfinal.datasource.LoanLocalDataSource
import com.vander.trabalho.trabfinal.db.dao.LoanDao
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.ui.FragmentHistoric
import com.vander.trabalho.trabfinal.ui.FragmentLoan
import com.vander.trabalho.trabfinal.ui.FragmentPeople
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), FragmentPeople.LoadPeople, FragmentLoan.LoadLoan{

    lateinit var loanDAO: LoanDao
    lateinit var peopleDAO: PeopleDao
    lateinit var db: LoanLocalDataSource
    val session = SessionManager()


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configure(applicationContext)

        navView.setupWithNavController(
            Navigation.findNavController(this, R.id.fragmentContent)
        )

        val it = intent

        val msg = it.getStringExtra("teste")
        val idItem = it.getIntExtra("idItem", 5)


        if(msg!=null){
            if(msg.equals("teste")){
                Log.d("dwada", "ESTOU AAAAQUI")
               // val newFragment = FragmentPeople()
               // val transaction = supportFragmentManager.beginTransaction()
                //transaction.replace(R.id.fragmentContent, newFragment)
               // transaction.addToBackStack(null)
              //  transaction.commit()
               // navView.menu.findItem(2131230915).setChecked(true)
               // navView.checkItem(R.id.navigation_home)

                val item = navView.getMenu().findItem(R.id.peopleFragment)
                item.setCheckable(true);
                item.setChecked(true);
                val session = SessionManager()

                Log.d("ID MENU", session.getItem().toString())
                navView.selectedItemId = idItem

                navView.setOnNavigationItemSelectedListener {
                    NavigationUI.onNavDestinationSelected(
                        it,
                        Navigation.findNavController(this, R.id.peopleFragment)
                    )
                }
            }
        }

        navView.setOnNavigationItemSelectedListener {
            NavigationUI.onNavDestinationSelected(
                it,
                Navigation.findNavController(this, R.id.fragmentContent)
            )
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is FragmentPeople) {
            session.setItem(navView.selectedItemId)
            Log.d("id primeiro:2131230915 ", "id segundo:" + session.getItem())
            fragment.setOnHeadlineSelectedListener(this, peopleDAO, navView.selectedItemId)
        }else if (fragment is FragmentLoan) {
            fragment.setOnHeadlineSelectedListener(this, loanDAO, applicationContext)
        }else if(fragment is FragmentHistoric){
            fragment.setOnHeadlineSelectedListener(this, loanDAO)
        }
    }

    private fun configure(applicationContext: Context) {
        db = LoanLocalDataSource(applicationContext)
        peopleDAO = db.peopleDao
        loanDAO = db.loanDao
    }

    fun daoPeople():PeopleDao{
        return this.peopleDAO
    }

    override fun onResume() {
        super.onResume()
        Log.d("main activy", "ON RESUMEEEEEEEEEEEEEE")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("main activy", "ESSA ACTIVIY FOI DESTRUÍDA")
    }

    override fun onPause() {
        super.onPause()
        Log.d("main activy", "ESSA ACTIVIY FOI PAUSADA")
    }

    override fun loadLoan() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPeoplee() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


