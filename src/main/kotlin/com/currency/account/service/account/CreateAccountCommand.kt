package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

data class CreateAccountCommand(
        val firstName: String,
        val lastName: String,
        val pesel: Pesel,
        val balance: BigDecimal
){
  fun toAccount() = Account(firstName, lastName, pesel , setOf(SubAccount(Money(Currency.PLN, balance))))
}

data class CreateAccountCommandDto(
        private val firstName: String,
        private val lastName: String,
        private val pesel: String,
        @ApiModelProperty(notes = "PLN")
        private val balance: BigDecimal
) {
  fun toCommand() = CreateAccountCommand(firstName,lastName, Pesel(pesel), balance)
}



