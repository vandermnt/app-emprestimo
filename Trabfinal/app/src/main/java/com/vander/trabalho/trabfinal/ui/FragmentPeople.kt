package com.vander.trabalho.trabfinal.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.AddPeopleActivity
import com.vander.trabalho.trabfinal.MainActivity
import com.vander.trabalho.trabfinal.R
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.entities.People
import com.vander.trabalho.trabfinal.ui.adapters.PeopleAdapter
import com.vander.trabalho.trabfinal.ui.adapters.PeopleAdapterListener
import kotlinx.android.synthetic.main.fragment_people.*

class FragmentPeople : Fragment(), PeopleAdapterListener {

    override fun confirmationLoan(people: People) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var loadpeople: MainActivity
    lateinit var callback: LoadPeople
    lateinit var peopleDAO: PeopleDao
    lateinit var adapter: PeopleAdapter
    var idItem = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("da", "estou no onCREATE DO FRAGMENT")
        loadpeople = activity as MainActivity
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            Log.d("da", "estou no onATTACH DO FRAGMENT")

        } catch (e: ClassCastException) {
            throw ClassCastException("$activity ")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("da", "estou no onDESTROY DO FRAGMENT")
    }

    fun setOnHeadlineSelectedListener(callback: LoadPeople, peopleDAO: PeopleDao, idItem: Int) {
        this.peopleDAO = peopleDAO
        this.callback = callback
        this.idItem = idItem
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("da", "estou no ONVIEWCREATED DO FRAGMENT")

        loadpeople.onAttachFragment(this)
        loadPeople()

    }

    interface LoadPeople {
        fun loadPeoplee()
    }

    override fun onResume() {
        super.onResume()

        addPeople.setOnClickListener(){
            val intent = Intent(activity, AddPeopleActivity::class.java)
            intent.putExtra("idItem", idItem)
            startActivity(intent)
        }
    }

     fun loadPeople() {
        val people = peopleDAO.getAll()
        val flag = true

        adapter = PeopleAdapter(people.toMutableList(), flag, this)

        listPeoples.adapter = adapter

        listPeoples.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
     }

    override fun viewPeople(people: People) {
    }

}

