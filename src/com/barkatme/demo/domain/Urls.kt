package com.barkatme.demo.domain

object Urls {

    const val signIn = "/auth/sign_in"
    const val signUp = "/auth/sign_up"
    const val signOut = "/auth/sign_out"

    const val viewers = "viewers"
    const val viewersById = "viewers/{userId}"
    const val deleteViewer = "viewers/{userId}"
    const val deleteViewerById = "viewers/{userId}/{viewerId}"
}