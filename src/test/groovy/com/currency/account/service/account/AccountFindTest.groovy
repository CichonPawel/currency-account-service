package com.currency.account.service.account

import com.currency.account.service.DynamicClock
import com.currency.account.service.ResourceNotFoundException
import com.currency.account.service.account.pesel.Pesel
import spock.lang.Specification

import java.time.LocalDateTime

class AccountFindTest extends Specification {

    def FIRST_NAME = "Rafał"
    def LAST_NAME = "Kowalski"
    def PESEL = new Pesel('02211036678')
    def BALANCE = BigDecimal.valueOf(123.45)
    def SUB_ACCOUNTS = [new SubAccount(Currency.PLN, BALANCE)] as Set
    def clock = new DynamicClock(LocalDateTime.of(2020, 01, 10, 13, 30))
    def repository = new InMemoryAccountRepository()
    def facade = new AccountModule().accountFacade(repository, clock)

    def "should find account when exist"() {
        given:
        repository.createAccount(new Account(FIRST_NAME, LAST_NAME, PESEL, SUB_ACCOUNTS))
        when:
        def account = facade.find(PESEL)
        then:
        account.firstName == FIRST_NAME
        account.lastName == LAST_NAME
        account.pesel == PESEL
        account.subAccounts == SUB_ACCOUNTS
    }

    def "should thrown exp when not exist"() {
        when:
        facade.find(PESEL)
        then:
        thrown(ResourceNotFoundException)
    }

}
