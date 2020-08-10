package com.currency.account.service.account

import com.currency.account.service.exchange.ExchangeExternalCommand
import com.currency.account.service.pesel.Pesel
import mu.KotlinLogging

class AccountFacade(private val accountCreator: AccountCreator,
                    private val accountFinder: AccountFinder,
                    private val balanceChanger: BalanceChanger) {

  private val log = KotlinLogging.logger {}

  fun createAccount(command: CreateAccountCommand): Account {
    log.info("Trying create account  by command $command")
    return accountCreator.create(command)
  }

  fun find(pesel: Pesel): Account {
    log.info("Trying find account for with pesel $pesel")
    return accountFinder.find(pesel)
  }

  fun exchange(command: ExchangeExternalCommand) = balanceChanger.changeBalance(command)
}