package com.currency.account.service.account

import com.currency.account.service.ValidationException
import com.currency.account.service.pesel.Pesel
import java.time.Clock
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class LegalAgeValidator(private val clock: Clock) {

  companion object {
    const val LEGAL_AGE: Int = 18
  }

  fun valid(pesel: Pesel) {
    if (pesel.birthDate().until(LocalDate.now(clock), ChronoUnit.YEARS) < LEGAL_AGE) {
      throw ValidationException("User with pesel ${pesel.value} is not of legal age")
    }
  }


}