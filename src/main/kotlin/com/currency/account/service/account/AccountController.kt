package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/accounts")
class AccountController(private val accountFacade: AccountFacade) {

  @PostMapping
  fun createAccount(@RequestBody commandDto: CreateAccountCommandDto)
          = accountFacade.createAccount(commandDto.toCommand())

  @GetMapping("/{pesel}")
  fun getAccount(@PathVariable pesel :String)
          = accountFacade.find(Pesel(pesel)).toDto()



}


