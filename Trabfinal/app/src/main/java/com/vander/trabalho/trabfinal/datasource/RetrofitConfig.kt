package com.vander.trabalho.trabfinal.datasource

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import com.vander.trabalho.trabfinal.network.CEPService


class RetrofitConfig(){

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://ws.matheuscastiglioni.com.br/ws/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun getCEPService(): CEPService {
        return this.retrofit.create(CEPService::class.java)
    }
}