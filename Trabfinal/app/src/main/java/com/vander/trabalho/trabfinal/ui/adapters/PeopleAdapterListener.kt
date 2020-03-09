package com.vander.trabalho.trabfinal.ui.adapters

import com.vander.trabalho.trabfinal.entities.People

interface PeopleAdapterListener{
    fun confirmationLoan(people: People)
    fun viewPeople(people: People)
}