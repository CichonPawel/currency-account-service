package com.currency.account.service

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*


@RestControllerAdvice
class GlobalErrorHandler {

  @ExceptionHandler(ResourceAlreadyExistException::class)
  @ResponseStatus(HttpStatus.CONFLICT)
  fun handleResourceAlreadyExistException(e: ResourceAlreadyExistException): ErrorResponse {
    return ErrorResponse("Resource already exist", stackTrace(e))
  }

  @ExceptionHandler(ValidationException::class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  fun handleValidationException(e: ValidationException): ErrorResponse {
    return ErrorResponse("Wrong entity", stackTrace(e))
  }

  @ExceptionHandler(ResourceNotFoundException::class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  fun handleResourceNotFoundException(e: ResourceNotFoundException): ErrorResponse {
    return ErrorResponse("Resource not found", stackTrace(e))
  }

  private fun stackTrace(e: Throwable): String {
    return Arrays.toString(e.stackTrace)
  }

  data class ErrorResponse(val message: String,
                           val details: String)

}

class ResourceAlreadyExistException(resourceId: String) : RuntimeException("Resource with id $resourceId already exist")
class ResourceNotFoundException(resourceId: String) : RuntimeException("Resource not found for id $resourceId")
class ValidationException(message: String) : RuntimeException(message)