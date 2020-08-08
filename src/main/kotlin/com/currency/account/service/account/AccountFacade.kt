package com.currency.account.service.account

class AccountFacade(private val accountCreator: AccountCreator) {
  fun createAccount(command: CreateAccountCommand): Account = accountCreator.create(command)
}