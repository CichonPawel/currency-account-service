package com.currency.account.service.account

import com.currency.account.service.ResourceNotFoundException
import com.currency.account.service.pesel.Pesel

class AccountFinder(private val accountRepository: AccountRepository) {
  fun find(pesel: Pesel)=
    accountRepository.getAccount(pesel) ?: throw ResourceNotFoundException(pesel.value)



}
