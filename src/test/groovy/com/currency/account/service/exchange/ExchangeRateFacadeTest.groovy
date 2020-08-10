package com.currency.account.service.exchange

import com.currency.account.service.DynamicClock
import com.currency.account.service.ResourceNotFoundException
import com.currency.account.service.ValidationException
import com.currency.account.service.account.Account
import com.currency.account.service.account.AccountModule
import com.currency.account.service.account.InMemoryAccountRepository
import com.currency.account.service.account.Money
import com.currency.account.service.account.SubAccount
import com.currency.account.service.nbp.NbpClient
import com.currency.account.service.pesel.Pesel
import spock.lang.Specification

import java.time.LocalDateTime

import static com.currency.account.service.account.Currency.*

class ExchangeRateFacadeTest extends Specification {

    def FIRST_NAME = "Rafał"
    def LAST_NAME = "Kowalski"
    def PESEL = new Pesel('02211036678')
    def USD_RATE = BigDecimal.valueOf(3.7302)
    def nbpClient = Stub(NbpClient)
    def clock = new DynamicClock(LocalDateTime.of(2020, 01, 10, 13, 30))
    def repository = new InMemoryAccountRepository()
    def accountFacade = new AccountModule().accountFacade(repository, clock)
    def exchangeRateFacade = new ExchangeModule().exchangeRateFacade(nbpClient, accountFacade)

    def setup() {
        nbpClient.getExchangeRateAgainstZloty(USD) >> USD_RATE
    }

    def "should exchange PLN to USD"() {
        given:
        createAccountWithSubAccount(new Money(PLN, BigDecimal.valueOf(100.00)))

        when:
        exchangeRateFacade.exchange(new ExchangeCommand(PLN, USD, BigDecimal.valueOf(100.00)), PESEL)

        then:
        def account = repository.getAccount(PESEL)
        account.balanceCurrency(PLN) == BigDecimal.ZERO
        account.balanceCurrency(USD)== BigDecimal.valueOf(26.8082)
    }

    def "should exchange USD to PLN"() {
        given:
        createAccountWithSubAccount(new Money(USD, BigDecimal.valueOf(26.8082)))

        when:
        exchangeRateFacade.exchange(new ExchangeCommand(USD, PLN, BigDecimal.valueOf(26.8082)), PESEL)

        then:
        def account = repository.getAccount(PESEL)
        account.balanceCurrency(PLN) == BigDecimal.valueOf(99.9999)
        account.balanceCurrency(USD) == BigDecimal.ZERO
    }

    def "should throw exp when account not exist"() {
        when:
        exchangeRateFacade.exchange(new ExchangeCommand(USD, PLN, BigDecimal.valueOf(26.8082)), PESEL)

        then:
        thrown(ResourceNotFoundException)
    }

    def "should throw exp when trying exchange same currency"(){
        given:
        createAccountWithSubAccount(new Money(PLN, BigDecimal.valueOf(100.00)))

        when:
        exchangeRateFacade.exchange(new ExchangeCommand(PLN, PLN, BigDecimal.valueOf(100.00)), PESEL)

        then:
        thrown(ValidationException)
    }

    def "should throw exp when trying to exchange more money than we have"(){
        given:
        createAccountWithSubAccount(new Money(PLN, BigDecimal.valueOf(100.00)))

        when:
        exchangeRateFacade.exchange(new ExchangeCommand(PLN, USD, BigDecimal.valueOf(101.00)), PESEL)

        then:
        thrown(ValidationException)
    }

    private Account createAccountWithSubAccount(Money money) {
        repository.createAccount(new Account(FIRST_NAME, LAST_NAME, PESEL, [new SubAccount(money)] as Set))
    }


}
