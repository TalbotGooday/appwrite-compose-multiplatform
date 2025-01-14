package io.appwrite.extensions

import androidx.activity.ComponentActivity
import io.appwrite.WebAuthComponent
import io.appwrite.enums.OAuthProvider
import io.appwrite.services.Account

suspend fun Account.createOAuth2Session(
    activity: ComponentActivity,
    provider: OAuthProvider,
    success: String?,
    failure: String?,
    scopes: List<String>?
) {
    createOAuth2Session(WebAuthComponent(activity), provider, success, failure, scopes)
}