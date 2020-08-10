package com.currency.account.service.exchange

import com.currency.account.service.account.Currency
import com.currency.account.service.nbp.NbpClient
import java.math.BigDecimal
import java.math.RoundingMode

interface ExchangeRateService {
  fun getExchangeRate(sourceCurrency: Currency, targetCurrency: Currency): BigDecimal
}

class ExchangeRateNBPService(private val nbpClient: NbpClient) : ExchangeRateService {

  override fun getExchangeRate(sourceCurrency: Currency, targetCurrency: Currency): BigDecimal {
    val targetCurrencyRate = getExchangeRateAgainstZloty(targetCurrency)
    val sourceCurrencyRate = getExchangeRateAgainstZloty(sourceCurrency)
    return targetCurrencyRate.divide(sourceCurrencyRate,8, RoundingMode.HALF_UP)
  }

  private fun getExchangeRateAgainstZloty(currency: Currency): BigDecimal {
    if (currency == Currency.PLN) {
      return BigDecimal.ONE
    }
    return nbpClient.getExchangeRateAgainstZloty(currency)
  }
}
