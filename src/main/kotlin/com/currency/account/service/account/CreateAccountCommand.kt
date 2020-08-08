package com.currency.account.service.account

import java.math.BigDecimal

data class CreateAccountCommand(
        val firstName: String,
        val lastName: String,
        val pesel: Pesel,
        val balance: BigDecimal
){
  fun toAccount() = Account(firstName, lastName, pesel , setOf(SubAccount(Currency.PLN, balance)))
}



