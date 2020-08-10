package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import java.math.BigDecimal

data class Account(
        val firstName: String,
        val lastName: String,
        val pesel: Pesel,
        val subAccounts: Set<SubAccount>
) {
  fun toDto() = AccountDto(firstName, lastName, pesel.value, subAccounts)
  fun balanceCurrency(currency: Currency): BigDecimal = subAccounts.find { it.money.currency == currency }
                  ?.money?.value
                  ?: BigDecimal.ZERO
}

data class SubAccount(val money: Money)

data class Money(val currency: Currency, val value: BigDecimal)

enum class Currency {
  PLN, USD
}

data class AccountDto(
        val firstName: String,
        val lastName: String,
        val pesel: String,
        val subAccounts: Set<SubAccount>
)