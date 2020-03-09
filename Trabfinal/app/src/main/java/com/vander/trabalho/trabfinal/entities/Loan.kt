package com.vander.trabalho.trabfinal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loan")
data class Loan(
    var name_item: String,
    var data_loan: String,
    var data_devolution: String,
    var status: String,
    var id_people: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun equals(other: Any?) =
        other != null && (this === other || (this.id != 0L && this.id == (other as Loan).id))
}