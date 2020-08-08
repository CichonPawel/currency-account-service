package com.currency.account.service.account

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class CreateAccountCommand(
        val firstName: String,
        val lastName: String,
        val pesel: Pesel
) {

}



