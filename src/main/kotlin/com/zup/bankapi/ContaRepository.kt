package com.zup.bankapi

import org.springframework.data.jpa.repository.JpaRepository

interface ContaRepository : JpaRepository <Conta, Long> {
}