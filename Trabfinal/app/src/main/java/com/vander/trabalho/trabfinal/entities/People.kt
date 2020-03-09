package com.vander.trabalho.trabfinal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "people")
data class People(
    var name: String,
    var date_nasc: String,
    var tel: String,
    var cep: String,
    var bairro: String,
    var endereco: String

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun equals(other: Any?) =
        other != null && (this === other || (this.id != 0L && this.id == (other as Loan).id))
}

