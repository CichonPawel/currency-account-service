package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import java.math.BigDecimal

data class Account(
        val firstName: String,
        val lastName: String,
        val pesel: Pesel,
        val subAccounts: Set<SubAccount>
){
  fun toDto() = AccountDto(firstName, lastName, pesel.value, subAccounts)
}

data class SubAccount(
        val currency: Currency,
        val balance: BigDecimal
)

enum class Currency {
  PLN, USD
}

data class AccountDto(
        val firstName: String,
        val lastName: String,
        val pesel: String,
        val subAccounts: Set<SubAccount>
)