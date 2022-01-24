package org.cardna.data.remote.model.login

data class RequestSignUpEmailData(
    val email: String,
    val name: String,
    val password: String,
)
