package io.appwrite.extensions

import io.appwrite.WebAuthComponent
import io.appwrite.enums.OAuthProvider
import io.appwrite.services.Account

suspend fun Account.createOAuth2Session(
    provider: OAuthProvider,
    success: String?,
    failure: String?,
    scopes: List<String>?
) {
    createOAuth2Session(WebAuthComponent(), provider, success, failure, scopes)
}