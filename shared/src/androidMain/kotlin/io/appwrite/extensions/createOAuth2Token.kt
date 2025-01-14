package io.appwrite.extensions

import android.app.Activity
import androidx.activity.ComponentActivity
import io.appwrite.WebAuthComponent
import io.appwrite.enums.OAuthProvider
import io.appwrite.services.Account

suspend fun Account.createOAuth2Token(
    activity: ComponentActivity,
    provider: OAuthProvider,
    success: String?,
    failure: String?,
    scopes: List<String>?
) {
    createOAuth2Token(WebAuthComponent(activity), provider, success, failure, scopes)
}