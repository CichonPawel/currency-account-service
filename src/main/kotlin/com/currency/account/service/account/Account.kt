package com.currency.account.service.account

import java.math.BigDecimal
import java.time.LocalDate

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

data class Pesel(
        val value: String
) {
  fun birthDate(): LocalDate {
    val decade = value.subInt(0, 2)
    val mount = value.subInt(2, 4)
    val day = value.subInt(4, 6)
    return when (value.subInt(2, 3)) {
      0, 1 -> LocalDate.of(1900 + decade, mount, day)
      2, 3 -> LocalDate.of(2000 + decade, mount - 20, day)
      4, 5 -> LocalDate.of(2100 + decade, mount - 40, day)
      6, 7 -> LocalDate.of(2200 + decade, mount - 60, day)
      8, 9 -> LocalDate.of(1800 + decade, mount - 80, day)
      else -> throw IllegalArgumentException()
    }
  }
}

private fun String.subInt(indexStart: Int, indexEnd: Int) = this.substring(indexStart, indexEnd).toInt()