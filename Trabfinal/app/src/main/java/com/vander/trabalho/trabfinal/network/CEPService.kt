package com.vander.trabalho.trabfinal.network

import com.vander.trabalho.trabfinal.entities.CEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CEPService {

    @GET("cep/find/{cep}/json")
    fun buscarCEP(@Path("cep") cep: String): Call<CEP>
}