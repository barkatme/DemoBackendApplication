package com.barkatme.demo.infrastructure.model.exception

import java.lang.RuntimeException

class InvalidCredentialsException(message: String) : RuntimeException(message)