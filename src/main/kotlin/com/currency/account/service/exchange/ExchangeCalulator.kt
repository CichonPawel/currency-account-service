package com.currency.account.service.exchange

import mu.KotlinLogging
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeCalculator {

  companion object{
    private val log = KotlinLogging.logger {}
    fun calculate(exchangeRate: BigDecimal, value: BigDecimal): BigDecimal {

      val resoult = value.divide(exchangeRate, 4, RoundingMode.HALF_DOWN)
      log.debug { "Exchange rate of $exchangeRate for $value is $resoult" }
      return resoult
    }
  }
}
