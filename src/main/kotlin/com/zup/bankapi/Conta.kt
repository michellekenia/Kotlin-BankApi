package com.zup.bankapi

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "conta")
data class Conta(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val document: String,
    val phone: String
)