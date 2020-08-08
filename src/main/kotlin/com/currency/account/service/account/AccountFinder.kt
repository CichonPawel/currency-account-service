package com.currency.account.service.account

import com.currency.account.service.ResourceNotFoundException

class AccountFinder(private val accountRepository: AccountRepository) {
  fun find(pesel: Pesel)=
    accountRepository.getAccount(pesel) ?: throw ResourceNotFoundException(pesel.value)



}
