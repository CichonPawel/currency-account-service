package com.currency.account.service.account

import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
@RequestMapping("/account")
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


data class CreateAccountCommandDto(
        private val firstName: String,
        private val lastName: String,
        private val pesel: String,
        @ApiModelProperty(notes = "PLN")
        private val balance: BigDecimal
) {
  fun toCommand() = CreateAccountCommand(firstName,lastName, Pesel(pesel), balance)
}