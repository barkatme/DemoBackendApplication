package com.barkatme.demo.model.exception

import java.lang.RuntimeException

class InvalidCredentialsException(message: String) : RuntimeException(message)