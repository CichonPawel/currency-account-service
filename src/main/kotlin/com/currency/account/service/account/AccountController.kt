package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/accounts")
class AccountController(private val accountFacade: AccountFacade) {

  @ApiOperation(value = "create account")
  @ApiResponses(value = [
    ApiResponse(code = 200, message = "Success"),
    ApiResponse(code = 409, message = "Account with this pesel currently exists")
  ])
  @PostMapping
  fun createAccount(@RequestBody commandDto: CreateAccountCommandDto)
          = accountFacade.createAccount(commandDto.toCommand())


  @ApiOperation(value = "get account")
  @ApiResponses(value = [
    ApiResponse(code = 200, message = "Success"),
    ApiResponse(code = 404, message = "Not found account")
  ])
  @GetMapping("/{pesel}")
  fun getAccount(@PathVariable pesel :String)
          = accountFacade.find(Pesel(pesel)).toDto()



}


