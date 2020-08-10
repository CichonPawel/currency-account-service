package com.currency.account.service.exchange

import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeCalculator {
  companion object{
    fun calculate(exchangeRate: BigDecimal, value: BigDecimal): BigDecimal = value.divide(exchangeRate, 4, RoundingMode.HALF_DOWN)
  }
}
