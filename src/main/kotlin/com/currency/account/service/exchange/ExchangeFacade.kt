package com.currency.account.service.exchange

import com.currency.account.service.pesel.Pesel
import mu.KotlinLogging

class ExchangeFacade(private val exchangeService: ExchangeService){
  private val log = KotlinLogging.logger {}

  fun exchange(command: ExchangeCommand, pesel: Pesel) {
    log.info { "trying exchange currency for account with pesel $pesel by command $command" }
    exchangeService.exchange(command, pesel)
  }


}