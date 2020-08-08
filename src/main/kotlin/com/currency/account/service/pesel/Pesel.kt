package com.currency.account.service.pesel

import java.time.LocalDate

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