package com.currency.account.service.exchange

import com.currency.account.service.account.AccountFacade
import com.currency.account.service.pesel.Pesel
import mu.KotlinLogging
import java.math.BigDecimal

class ExchangeService(private val accountFacade: AccountFacade,
                      private val exchangeRateService: ExchangeRateService) {

  fun exchange(command: ExchangeCommand, pesel: Pesel) {
    val account = accountFacade.find(pesel)
    ExchangeValidator.valid(account, command)
    accountFacade.exchange(command.toExternalCommand(pesel, calculateTargetValue(command)))
  }

  private fun calculateTargetValue(command: ExchangeCommand): BigDecimal {
    val exchangeRate = exchangeRateService.getExchangeRate(command.sourceCurrency, command.targetCurrency)
    return ExchangeCalculator.calculate(exchangeRate, command.value)
  }
}