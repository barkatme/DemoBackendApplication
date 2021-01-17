@file:Suppress("DEPRECATION")

package com.barkatme.demo.infrastructure.routing

import com.barkatme.demo.domain.model.User
import com.barkatme.demo.domain.repository.UserRepository
import com.barkatme.demo.domain.usecase.auth.SignUpUseCase
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.css.CSSBuilder
import kotlinx.css.Color
import kotlinx.css.TextAlign
import kotlinx.css.em
import kotlinx.html.*
import org.koin.ktor.ext.get

fun Routing.homeRouting() {

    val signUpUseCase: SignUpUseCase = get()

    get("/") {

        signUpUseCase.signUp(User(email = "testUser", passwordHash = "passwordHash")) { user -> user.passwordHash }

        call.respondHtml {
            head {
                meta {
                    charset = Charsets.UTF_8.name()
                }
            }
            body {
                style {
                    +css {
                        kotlinx.css.body {
                            backgroundColor = Color.black
                            color = Color.white
                            fontSize = 4.em
                            textAlign = TextAlign.center
                        }
                        kotlinx.css.h4 {
                            color = Color.green
                        }
                        kotlinx.css.i {
                            backgroundColor = Color("#222")
                        }
                    }
                }
                h4 {
                    +"BarkAtMe Demo Backend Application"
                }
                p {
                    +"demo application to provide rest api and research features"
                }
            }
        }
    }
}

private inline fun css(builder: CSSBuilder.() -> Unit): String = CSSBuilder().apply(builder).toString()