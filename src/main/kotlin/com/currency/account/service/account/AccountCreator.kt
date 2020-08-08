package com.currency.account.service.account

import com.currency.account.service.ResourceAlreadyExistException


class AccountCreator(private val accountRepository: AccountRepository,
                     private val legalAgeValidator: LegalAgeValidator) {

  fun create(command: CreateAccountCommand): Account {
    PeselValidator.valid(command.pesel)
    legalAgeValidator.valid(command.pesel)

    accountRepository.getAccount(command.pesel)
            ?.let { throw ResourceAlreadyExistException(command.pesel.value) }
    return accountRepository.createAccount(command.toAccount())
  }

}
