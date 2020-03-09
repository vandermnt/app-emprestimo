package com.vander.trabalho.trabfinal.ui


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.AddLoanActivity
import com.vander.trabalho.trabfinal.db.dao.PeopleDao
import com.vander.trabalho.trabfinal.entities.People
import com.vander.trabalho.trabfinal.ui.adapters.PeopleAdapter
import com.vander.trabalho.trabfinal.ui.adapters.PeopleAdapterListener
import kotlinx.android.synthetic.main.fragment_search_people.*

class FragmentSearchPeople : Fragment(), PeopleAdapterListener {

    lateinit var addloan: AddLoanActivity
    lateinit var peopleDAO: PeopleDao
    lateinit var adapter: PeopleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.vander.trabalho.trabfinal.R.layout.fragment_search_people, container, false)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try { addloan = activity as AddLoanActivity }
        catch (e: ClassCastException) { throw ClassCastException("$activity deve implementar CreateEmailListener") }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addloan.onAttachFragment(this)
        loadPeople()
    }

    fun setOnHeadlineSelectedListener(peopleDAO: PeopleDao) {
        this.peopleDAO = peopleDAO
    }

    interface LoadPeople{
        fun loadpeople(){}
    }

    fun loadPeople() {
        val people = peopleDAO.getAll()
        var flag = false

        adapter = PeopleAdapter(people.toMutableList(), flag, this)

        listPeopleSearch.adapter = adapter

        listPeopleSearch.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
    }

    override fun confirmationLoan(people: People){
        val fragmentManager = activity!!.supportFragmentManager
        val fragmente = FragmentConfirmationLoan()
        val bundle = Bundle()

        bundle.putString("name_people", people.name)
        bundle.putString("data_nasc", people.date_nasc)
        bundle.putString("tel", people.tel)
        bundle.putString("cep", people.cep)
        bundle.putString("endereco", people.endereco)
        bundle.putString("bairro", people.bairro)
        bundle.putString("id_people", people.name)

        fragmente.setArguments(bundle)

        fragmentManager.beginTransaction().replace(com.vander.trabalho.trabfinal.R.id.containerr, fragmente).commit()
    }

    override fun viewPeople(people: People) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
