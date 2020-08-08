package com.currency.account.service.pesel

import com.currency.account.service.ValidationException
import java.time.DateTimeException

class PeselValidator() {

  companion object{
    //todo add valid checksum
    fun valid(pesel: Pesel) {
      validSize(pesel)
      validIsNumber(pesel)
      validDateFormat(pesel)
    }

    private fun validSize(pesel: Pesel) {
      if (pesel.value.length != 11) {
        throw ValidationException("Pesel ${pesel.value} has the wrong number of characters")
      }
    }

    private fun validDateFormat(pesel: Pesel) {
      try { pesel.birthDate()
      } catch (e: DateTimeException) {
        throw ValidationException("Part of the date of birth pesel (${pesel.value.substring(0, 5)}) is in wrong format")
      }
    }

    private fun validIsNumber(pesel: Pesel) {
      pesel.value.toLongOrNull() ?: throw ValidationException("Pesel ${pesel.value} is not number")
    }
  }
}