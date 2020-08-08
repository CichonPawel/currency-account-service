package com.currency.account.service.account

import java.util.concurrent.ConcurrentHashMap

interface AccountRepository {
  fun createAccount(command: CreateAccountCommand): Account
  fun getAccount(pesel: Pesel): Account?
}

class InMemoryAccountRepository : AccountRepository {

  private val accounts = ConcurrentHashMap<Pesel, Account>()

  override fun createAccount(command: CreateAccountCommand): Account {
    val account = command.toAccount()
    accounts[command.pesel] = account
    return account
  }

  override fun getAccount(pesel: Pesel): Account? = accounts[pesel]


}
