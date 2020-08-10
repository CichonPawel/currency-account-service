package com.currency.account.service.exchange

import com.currency.account.service.account.Currency
import com.currency.account.service.account.Money
import com.currency.account.service.pesel.Pesel
import java.math.BigDecimal

data class ExchangeExternalCommand(
        val pesel: Pesel,
        val sourceMoney: Money,
        val targetMoney: Money
)

data class ExchangeCommand(
        val sourceCurrency: Currency,
        val targetCurrency: Currency,
        val value: BigDecimal
) {
  fun toExternalCommand(pesel: Pesel, targetValue: BigDecimal)
          = ExchangeExternalCommand(pesel, Money(sourceCurrency, value.unaryMinus()), Money(targetCurrency, targetValue))

}