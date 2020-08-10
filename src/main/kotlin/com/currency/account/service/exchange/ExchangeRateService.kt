package com.currency.account.service.exchange

import com.currency.account.service.account.Currency
import com.currency.account.service.nbp.NbpClient
import mu.KotlinLogging
import java.math.BigDecimal
import java.math.RoundingMode

interface ExchangeRateService {
  fun getExchangeRate(sourceCurrency: Currency, targetCurrency: Currency): BigDecimal
}

class ExchangeRateNBPService(private val nbpClient: NbpClient) : ExchangeRateService {

  private val log = KotlinLogging.logger {}

  override fun getExchangeRate(sourceCurrency: Currency, targetCurrency: Currency): BigDecimal {
    val targetCurrencyRate = getExchangeRateAgainstZloty(targetCurrency)
    val sourceCurrencyRate = getExchangeRateAgainstZloty(sourceCurrency)
    val rate = targetCurrencyRate.divide(sourceCurrencyRate, 8, RoundingMode.HALF_UP)
    log.debug { "calculated rate $rate for $sourceCurrency/$targetCurrency" }
    return rate
  }

  private fun getExchangeRateAgainstZloty(currency: Currency): BigDecimal {
    if (currency == Currency.PLN) {
      return BigDecimal.ONE
    }
    val rate = nbpClient.getExchangeRateAgainstZloty(currency)
    log.debug { "nbp return rate $rate for currency" }
    return rate
  }
}
