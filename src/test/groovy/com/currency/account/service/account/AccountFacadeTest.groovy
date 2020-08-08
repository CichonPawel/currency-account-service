package com.currency.account.service.account

import com.currency.account.service.ResourceAlreadyExistException
import com.currency.account.service.ValidationException
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class AccountFacadeTest extends Specification {

    def FIRST_NAME = "Rafał"
    def LAST_NAME = "Kowalski"
    def PESEL = new Pesel('02211036678')
    def clock = new DynamicClock(LocalDateTime.of(2020, 01, 10, 13, 30))
    def facade = new AccountModule().accountFacade(new InMemoryAccountRepository(), clock)

    def "create account should create correctly"() {
        when:
        def account = facade.createAccount(new CreateAccountCommand(FIRST_NAME, LAST_NAME, PESEL))
        then:
        account.firstName == FIRST_NAME
        account.lastName == LAST_NAME
        account.pesel == PESEL
    }

    def "trying to create an account with the same PESEL number should end with an error"() {
        given:
        facade.createAccount(new CreateAccountCommand(FIRST_NAME, LAST_NAME, PESEL))
        when:
        facade.createAccount(new CreateAccountCommand("Bartłomiej", "Jankowski", PESEL))
        then:
        thrown(ResourceAlreadyExistException)
    }

    @Unroll
    def "trying to create an account with wrong PESEL (#pesel) should end with an error message '#expectedMessage'"() {
        when:
        facade.createAccount(new CreateAccountCommand(FIRST_NAME, LAST_NAME, new Pesel(pesel)))
        then:
        def exception = thrown(expectedException)
        expectedMessage == exception.message
        where:
        pesel          | expectedException   | expectedMessage
        '022110366781' | ValidationException | "Pesel $pesel has the wrong number of characters"
        '0221103667X'  | ValidationException | "Pesel $pesel is not number"
        '02213236678'  | ValidationException | "Part of the date of birth pesel (${pesel.substring(0,5)}) is in wrong format"
        '02211136678'  | ValidationException | "User with pesel $pesel is not of legal age"
    }

}
