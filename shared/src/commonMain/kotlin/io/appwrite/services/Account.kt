package io.appwrite.services

import io.appwrite.Client
import io.appwrite.Service
import io.appwrite.enums.*
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.classOf
import io.appwrite.WebAuthComponent
import io.appwrite.models.IdentityList
import io.appwrite.models.Jwt
import io.appwrite.models.LogList
import io.appwrite.models.MfaChallenge
import io.appwrite.models.MfaFactors
import io.appwrite.models.MfaRecoveryCodes
import io.appwrite.models.MfaType
import io.appwrite.models.Preferences
import io.appwrite.models.Session
import io.appwrite.models.SessionList
import io.appwrite.models.Token
import io.appwrite.models.User
import io.appwrite.webInterface.UrlParser
import io.ktor.client.plugins.cookies.cookies
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import io.ktor.http.Cookie
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.KClass
import kotlin.jvm.JvmOverloads

/**
 * The Account service allows you to authenticate and manage a user account.
 **/
abstract class Account(client: Client) : Service(client) {

    /**
     * Get account
     *
     * Get the currently logged in user.
     *
     * @return [User<T>]
     */
    suspend fun <T: Any> get(
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Get account
     *
     * Get the currently logged in user.
     *
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun get(
    ): User<Map<String, Any>> = get(
        nestedType = classOf(),
    )

    /**
     * Create account
     *
     * Use this endpoint to allow a new user to register a new account in your project. After the user registration completes successfully, you can use the [/account/verfication](https://appwrite.io/docs/references/cloud/client-web/account#createVerification) route to start verifying the user email address. To allow the new user to login to their new account, you need to create a new [account session](https://appwrite.io/docs/references/cloud/client-web/account#createEmailSession).
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param password New user password. Must be between 8 and 256 chars.
     * @param name User name. Max length: 128 chars.
     * @return [User<T>]
     */
    @JvmOverloads
    suspend fun <T: Any> create(
        userId: String,
        email: String,
        password: String,
        name: String? = null,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "email" to email,
            "password" to password,
            "name" to name,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Create account
     *
     * Use this endpoint to allow a new user to register a new account in your project. After the user registration completes successfully, you can use the [/account/verfication](https://appwrite.io/docs/references/cloud/client-web/account#createVerification) route to start verifying the user email address. To allow the new user to login to their new account, you need to create a new [account session](https://appwrite.io/docs/references/cloud/client-web/account#createEmailSession).
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param password New user password. Must be between 8 and 256 chars.
     * @param name User name. Max length: 128 chars.
     * @return [User<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun create(
        userId: String,
        email: String,
        password: String,
        name: String? = null,
    ): User<Map<String, Any>> = create(
        userId,
        email,
        password,
        name,
        nestedType = classOf(),
    )

    /**
     * Update email
     *
     * Update currently logged in user account email address. After changing user address, the user confirmation status will get reset. A new confirmation email is not sent automatically however you can use the send confirmation email endpoint again to send the confirmation email. For security measures, user password is required to complete this request.This endpoint can also be used to convert an anonymous account to a normal one, by passing an email address and a new password.
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [User<T>]
     */
    suspend fun <T: Any> updateEmail(
        email: String,
        password: String,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/email"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update email
     *
     * Update currently logged in user account email address. After changing user address, the user confirmation status will get reset. A new confirmation email is not sent automatically however you can use the send confirmation email endpoint again to send the confirmation email. For security measures, user password is required to complete this request.This endpoint can also be used to convert an anonymous account to a normal one, by passing an email address and a new password.
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updateEmail(
        email: String,
        password: String,
    ): User<Map<String, Any>> = updateEmail(
        email,
        password,
        nestedType = classOf(),
    )

    /**
     * List Identities
     *
     * Get the list of identities for the currently logged in user.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: userId, provider, providerUid, providerEmail, providerAccessTokenExpiry
     * @return [IdentityList]
     */
    @JvmOverloads
    suspend fun listIdentities(
        queries: List<String>? = null,
    ): IdentityList {
        val apiPath = "/account/identities"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> IdentityList = {
            @Suppress("UNCHECKED_CAST")
            IdentityList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = IdentityList::class,
            converter,
        )
    }


    /**
     * Delete identity
     *
     * Delete an identity by its unique ID.
     *
     * @param identityId Identity ID.
     * @return [Any]
     */
    suspend fun deleteIdentity(
        identityId: String,
    ): Any {
        val apiPath = "/account/identities/{identityId}"
            .replace("{identityId}", identityId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * Create JWT
     *
     * Use this endpoint to create a JSON Web Token. You can use the resulting JWT to authenticate on behalf of the current user when working with the Appwrite server-side API and SDKs. The JWT secret is valid for 15 minutes from its creation and will be invalid if the user will logout in that time frame.
     *
     * @return [Jwt]
     */
    suspend fun createJWT(
    ): Jwt {
        val apiPath = "/account/jwts"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Jwt = {
            @Suppress("UNCHECKED_CAST")
            Jwt.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Jwt::class,
            converter,
        )
    }


    /**
     * List logs
     *
     * Get the list of latest security activity logs for the currently logged in user. Each log returns user IP address, location and date and time of log.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @return [LogList]
     */
    @JvmOverloads
    suspend fun listLogs(
        queries: List<String>? = null,
    ): LogList {
        val apiPath = "/account/logs"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> LogList = {
            @Suppress("UNCHECKED_CAST")
            LogList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = LogList::class,
            converter,
        )
    }


    /**
     * Update MFA
     *
     * Enable or disable MFA on an account.
     *
     * @param mfa Enable or disable MFA.
     * @return [User<T>]
     */
    suspend fun <T: Any> updateMFA(
        mfa: Boolean,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/mfa"

        val apiParams = mutableMapOf<String, Any?>(
            "mfa" to mfa,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update MFA
     *
     * Enable or disable MFA on an account.
     *
     * @param mfa Enable or disable MFA.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updateMFA(
        mfa: Boolean,
    ): User<Map<String, Any>> = updateMFA(
        mfa,
        nestedType = classOf(),
    )

    /**
     * Create Authenticator
     *
     * Add an authenticator app to be used as an MFA factor. Verify the authenticator using the [verify authenticator](/docs/references/cloud/client-web/account#updateMfaAuthenticator) method.
     *
     * @param type Type of authenticator. Must be `totp`
     * @return [MfaType]
     */
    suspend fun createMfaAuthenticator(
        type: io.appwrite.enums.AuthenticatorType,
    ): MfaType {
        val apiPath = "/account/mfa/authenticators/{type}"
            .replace("{type}", type.value)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaType = {
            @Suppress("UNCHECKED_CAST")
            MfaType.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaType::class,
            converter,
        )
    }


    /**
     * Verify Authenticator
     *
     * Verify an authenticator app after adding it using the [add authenticator](/docs/references/cloud/client-web/account#createMfaAuthenticator) method.
     *
     * @param type Type of authenticator.
     * @param otp Valid verification token.
     * @return [User<T>]
     */
    suspend fun <T: Any> updateMfaAuthenticator(
        type: io.appwrite.enums.AuthenticatorType,
        otp: String,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/mfa/authenticators/{type}"
            .replace("{type}", type.value)

        val apiParams = mutableMapOf<String, Any?>(
            "otp" to otp,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Verify Authenticator
     *
     * Verify an authenticator app after adding it using the [add authenticator](/docs/references/cloud/client-web/account#createMfaAuthenticator) method.
     *
     * @param type Type of authenticator.
     * @param otp Valid verification token.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updateMfaAuthenticator(
        type: io.appwrite.enums.AuthenticatorType,
        otp: String,
    ): User<Map<String, Any>> = updateMfaAuthenticator(
        type,
        otp,
        nestedType = classOf(),
    )

    /**
     * Delete Authenticator
     *
     * Delete an authenticator for a user by ID.
     *
     * @param type Type of authenticator.
     * @return [Any]
     */
    suspend fun deleteMfaAuthenticator(
        type: io.appwrite.enums.AuthenticatorType,
    ): Any {
        val apiPath = "/account/mfa/authenticators/{type}"
            .replace("{type}", type.value)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * Create MFA Challenge
     *
     * Begin the process of MFA verification after sign-in. Finish the flow with [updateMfaChallenge](/docs/references/cloud/client-web/account#updateMfaChallenge) method.
     *
     * @param factor Factor used for verification. Must be one of following: `email`, `phone`, `totp`, `recoveryCode`.
     * @return [MfaChallenge]
     */
    suspend fun createMfaChallenge(
        factor: io.appwrite.enums.AuthenticationFactor,
    ): MfaChallenge {
        val apiPath = "/account/mfa/challenge"

        val apiParams = mutableMapOf<String, Any?>(
            "factor" to factor,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaChallenge = {
            @Suppress("UNCHECKED_CAST")
            MfaChallenge.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaChallenge::class,
            converter,
        )
    }


    /**
     * Create MFA Challenge (confirmation)
     *
     * Complete the MFA challenge by providing the one-time password. Finish the process of MFA verification by providing the one-time password. To begin the flow, use [createMfaChallenge](/docs/references/cloud/client-web/account#createMfaChallenge) method.
     *
     * @param challengeId ID of the challenge.
     * @param otp Valid verification token.
     * @return [Any]
     */
    suspend fun updateMfaChallenge(
        challengeId: String,
        otp: String,
    ): Any {
        val apiPath = "/account/mfa/challenge"

        val apiParams = mutableMapOf<String, Any?>(
            "challengeId" to challengeId,
            "otp" to otp,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * List Factors
     *
     * List the factors available on the account to be used as a MFA challange.
     *
     * @return [MfaFactors]
     */
    suspend fun listMfaFactors(
    ): MfaFactors {
        val apiPath = "/account/mfa/factors"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaFactors = {
            @Suppress("UNCHECKED_CAST")
            MfaFactors.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaFactors::class,
            converter,
        )
    }


    /**
     * Get MFA Recovery Codes
     *
     * Get recovery codes that can be used as backup for MFA flow. Before getting codes, they must be generated using [createMfaRecoveryCodes](/docs/references/cloud/client-web/account#createMfaRecoveryCodes) method. An OTP challenge is required to read recovery codes.
     *
     * @return [MfaRecoveryCodes]
     */
    suspend fun getMfaRecoveryCodes(
    ): MfaRecoveryCodes {
        val apiPath = "/account/mfa/recovery-codes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaRecoveryCodes = {
            @Suppress("UNCHECKED_CAST")
            MfaRecoveryCodes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaRecoveryCodes::class,
            converter,
        )
    }


    /**
     * Create MFA Recovery Codes
     *
     * Generate recovery codes as backup for MFA flow. It&#039;s recommended to generate and show then immediately after user successfully adds their authehticator. Recovery codes can be used as a MFA verification type in [createMfaChallenge](/docs/references/cloud/client-web/account#createMfaChallenge) method.
     *
     * @return [MfaRecoveryCodes]
     */
    suspend fun createMfaRecoveryCodes(
    ): MfaRecoveryCodes {
        val apiPath = "/account/mfa/recovery-codes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaRecoveryCodes = {
            @Suppress("UNCHECKED_CAST")
            MfaRecoveryCodes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaRecoveryCodes::class,
            converter,
        )
    }


    /**
     * Regenerate MFA Recovery Codes
     *
     * Regenerate recovery codes that can be used as backup for MFA flow. Before regenerating codes, they must be first generated using [createMfaRecoveryCodes](/docs/references/cloud/client-web/account#createMfaRecoveryCodes) method. An OTP challenge is required to regenreate recovery codes.
     *
     * @return [MfaRecoveryCodes]
     */
    suspend fun updateMfaRecoveryCodes(
    ): MfaRecoveryCodes {
        val apiPath = "/account/mfa/recovery-codes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> MfaRecoveryCodes = {
            @Suppress("UNCHECKED_CAST")
            MfaRecoveryCodes.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = MfaRecoveryCodes::class,
            converter,
        )
    }


    /**
     * Update name
     *
     * Update currently logged in user account name.
     *
     * @param name User name. Max length: 128 chars.
     * @return [User<T>]
     */
    suspend fun <T: Any> updateName(
        name: String,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/name"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update name
     *
     * Update currently logged in user account name.
     *
     * @param name User name. Max length: 128 chars.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updateName(
        name: String,
    ): User<Map<String, Any>> = updateName(
        name,
        nestedType = classOf(),
    )

    /**
     * Update password
     *
     * Update currently logged in user password. For validation, user is required to pass in the new password, and the old password. For users created with OAuth, Team Invites and Magic URL, oldPassword is optional.
     *
     * @param password New user password. Must be at least 8 chars.
     * @param oldPassword Current user password. Must be at least 8 chars.
     * @return [User<T>]
     */
    @JvmOverloads
    suspend fun <T: Any> updatePassword(
        password: String,
        oldPassword: String? = null,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/password"

        val apiParams = mutableMapOf<String, Any?>(
            "password" to password,
            "oldPassword" to oldPassword,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update password
     *
     * Update currently logged in user password. For validation, user is required to pass in the new password, and the old password. For users created with OAuth, Team Invites and Magic URL, oldPassword is optional.
     *
     * @param password New user password. Must be at least 8 chars.
     * @param oldPassword Current user password. Must be at least 8 chars.
     * @return [User<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updatePassword(
        password: String,
        oldPassword: String? = null,
    ): User<Map<String, Any>> = updatePassword(
        password,
        oldPassword,
        nestedType = classOf(),
    )

    /**
     * Update phone
     *
     * Update the currently logged in user&#039;s phone number. After updating the phone number, the phone verification status will be reset. A confirmation SMS is not sent automatically, however you can use the [POST /account/verification/phone](https://appwrite.io/docs/references/cloud/client-web/account#createPhoneVerification) endpoint to send a confirmation SMS.
     *
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @param password User password. Must be at least 8 chars.
     * @return [User<T>]
     */
    suspend fun <T: Any> updatePhone(
        phone: String,
        password: String,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "phone" to phone,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update phone
     *
     * Update the currently logged in user&#039;s phone number. After updating the phone number, the phone verification status will be reset. A confirmation SMS is not sent automatically, however you can use the [POST /account/verification/phone](https://appwrite.io/docs/references/cloud/client-web/account#createPhoneVerification) endpoint to send a confirmation SMS.
     *
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @param password User password. Must be at least 8 chars.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updatePhone(
        phone: String,
        password: String,
    ): User<Map<String, Any>> = updatePhone(
        phone,
        password,
        nestedType = classOf(),
    )

    /**
     * Get account preferences
     *
     * Get the preferences as a key-value object for the currently logged in user.
     *
     * @return [Preferences<T>]
     */
    suspend fun <T: Any> getPrefs(
        nestedType: KClass<T>,
    ): Preferences<T> {
        val apiPath = "/account/prefs"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Preferences<T> = {
            @Suppress("UNCHECKED_CAST")
            Preferences.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Get account preferences
     *
     * Get the preferences as a key-value object for the currently logged in user.
     *
     * @return [Preferences<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun getPrefs(
    ): Preferences<Map<String, Any>> = getPrefs(
        nestedType = classOf(),
    )

    /**
     * Update preferences
     *
     * Update currently logged in user account preferences. The object you pass is stored as is, and replaces any previous value. The maximum allowed prefs size is 64kB and throws error if exceeded.
     *
     * @param prefs Prefs key-value JSON object.
     * @return [User<T>]
     */
    suspend fun <T: Any> updatePrefs(
        prefs: Any,
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/prefs"

        val apiParams = mutableMapOf<String, Any?>(
            "prefs" to prefs,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update preferences
     *
     * Update currently logged in user account preferences. The object you pass is stored as is, and replaces any previous value. The maximum allowed prefs size is 64kB and throws error if exceeded.
     *
     * @param prefs Prefs key-value JSON object.
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updatePrefs(
        prefs: Any,
    ): User<Map<String, Any>> = updatePrefs(
        prefs,
        nestedType = classOf(),
    )

    /**
     * Create password recovery
     *
     * Sends the user an email with a temporary secret key for password reset. When the user clicks the confirmation link he is redirected back to your app password reset URL with the secret key and email address values attached to the URL query string. Use the query string params to submit a request to the [PUT /account/recovery](https://appwrite.io/docs/references/cloud/client-web/account#updateRecovery) endpoint to complete the process. The verification link sent to the user&#039;s email address is valid for 1 hour.
     *
     * @param email User email.
     * @param url URL to redirect the user back to your app from the recovery email. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @return [Token]
     */
    suspend fun createRecovery(
        email: String,
        url: String,
    ): Token {
        val apiPath = "/account/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "url" to url,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Create password recovery (confirmation)
     *
     * Use this endpoint to complete the user account password reset. Both the **userId** and **secret** arguments will be passed as query parameters to the redirect URL you have provided when sending your request to the [POST /account/recovery](https://appwrite.io/docs/references/cloud/client-web/account#createRecovery) endpoint.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md) the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface.
     *
     * @param userId User ID.
     * @param secret Valid reset token.
     * @param password New user password. Must be between 8 and 256 chars.
     * @return [Token]
     */
    suspend fun updateRecovery(
        userId: String,
        secret: String,
        password: String,
    ): Token {
        val apiPath = "/account/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * List sessions
     *
     * Get the list of active sessions across different devices for the currently logged in user.
     *
     * @return [SessionList]
     */
    suspend fun listSessions(
    ): SessionList {
        val apiPath = "/account/sessions"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> SessionList = {
            @Suppress("UNCHECKED_CAST")
            SessionList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = SessionList::class,
            converter,
        )
    }


    /**
     * Delete sessions
     *
     * Delete all sessions from the user account and remove any sessions cookies from the end client.
     *
     * @return [Any]
     */
    suspend fun deleteSessions(
    ): Any {
        val apiPath = "/account/sessions"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * Create anonymous session
     *
     * Use this endpoint to allow a new user to register an anonymous account in your project. This route will also create a new session for the user. To allow the new user to convert an anonymous account to a normal account, you need to update its [email and password](https://appwrite.io/docs/references/cloud/client-web/account#updateEmail) or create an [OAuth2 session](https://appwrite.io/docs/references/cloud/client-web/account#CreateOAuth2Session).
     *
     * @return [Session]
     */
    suspend fun createAnonymousSession(
    ): Session {
        val apiPath = "/account/sessions/anonymous"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Create email password session
     *
     * Allow the user to login into their account by providing a valid email and password combination. This route will create a new session for the user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](https://appwrite.io/docs/authentication-security#limits).
     *
     * @param email User email.
     * @param password User password. Must be at least 8 chars.
     * @return [Session]
     */
    suspend fun createEmailPasswordSession(
        email: String,
        password: String,
    ): Session {
        val apiPath = "/account/sessions/email"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Update magic URL session
     *
     * Use this endpoint to create a session from token. Provide the **userId** and **secret** parameters from the successful response of authentication flows initiated by token creation. For example, magic URL and phone login.
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param secret Valid verification token.
     * @return [Session]
     */
    suspend fun updateMagicURLSession(
        userId: String,
        secret: String,
    ): Session {
        val apiPath = "/account/sessions/magic-url"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Update phone session
     *
     * Use this endpoint to create a session from token. Provide the **userId** and **secret** parameters from the successful response of authentication flows initiated by token creation. For example, magic URL and phone login.
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param secret Valid verification token.
     * @return [Session]
     */
    suspend fun updatePhoneSession(
        userId: String,
        secret: String,
    ): Session {
        val apiPath = "/account/sessions/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Create session
     *
     * Use this endpoint to create a session from token. Provide the **userId** and **secret** parameters from the successful response of authentication flows initiated by token creation. For example, magic URL and phone login.
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param secret Secret of a token generated by login methods. For example, the `createMagicURLToken` or `createPhoneToken` methods.
     * @return [Session]
     */
    suspend fun createSession(
        userId: String,
        secret: String,
    ): Session {
        val apiPath = "/account/sessions/token"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Get session
     *
     * Use this endpoint to get a logged in user&#039;s session using a Session ID. Inputting &#039;current&#039; will return the current session being used.
     *
     * @param sessionId Session ID. Use the string 'current' to get the current device session.
     * @return [Session]
     */
    suspend fun getSession(
        sessionId: String,
    ): Session {
        val apiPath = "/account/sessions/{sessionId}"
            .replace("{sessionId}", sessionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Update session
     *
     * Use this endpoint to extend a session&#039;s length. Extending a session is useful when session expiry is short. If the session was created using an OAuth provider, this endpoint refreshes the access token from the provider.
     *
     * @param sessionId Session ID. Use the string 'current' to update the current device session.
     * @return [Session]
     */
    suspend fun updateSession(
        sessionId: String,
    ): Session {
        val apiPath = "/account/sessions/{sessionId}"
            .replace("{sessionId}", sessionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Session = {
            @Suppress("UNCHECKED_CAST")
            Session.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Session::class,
            converter,
        )
    }


    /**
     * Delete session
     *
     * Logout the user. Use &#039;current&#039; as the session ID to logout on this device, use a session ID to logout on another device. If you&#039;re looking to logout the user on all devices, use [Delete Sessions](https://appwrite.io/docs/references/cloud/client-web/account#deleteSessions) instead.
     *
     * @param sessionId Session ID. Use the string 'current' to delete the current device session.
     * @return [Any]
     */
    suspend fun deleteSession(
        sessionId: String,
    ): Any {
        val apiPath = "/account/sessions/{sessionId}"
            .replace("{sessionId}", sessionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * Update status
     *
     * Block the currently logged in user account. Behind the scene, the user record is not deleted but permanently blocked from any access. To completely delete a user, use the Users API instead.
     *
     * @return [User<T>]
     */
    suspend fun <T: Any> updateStatus(
        nestedType: KClass<T>,
    ): User<T> {
        val apiPath = "/account/status"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> User<T> = {
            @Suppress("UNCHECKED_CAST")
            User.from(map = it as Map<String, Any>, nestedType)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = classOf(),
            converter,
        )
    }

    /**
     * Update status
     *
     * Block the currently logged in user account. Behind the scene, the user record is not deleted but permanently blocked from any access. To completely delete a user, use the Users API instead.
     *
     * @return [User<T>]
     */
    @Throws(AppwriteException::class, CancellationException::class)
    suspend fun updateStatus(
    ): User<Map<String, Any>> = updateStatus(
        nestedType = classOf(),
    )

    /**
     * Create push target
     *
     *
     *
     * @param targetId Target ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param identifier The target identifier (token, email, phone etc.)
     * @param providerId Provider ID. Message will be sent to this target from the specified provider ID. If no provider ID is set the first setup provider will be used.
     * @return [Target]
     */
    @JvmOverloads
    suspend fun createPushTarget(
        targetId: String,
        identifier: String,
        providerId: String? = null,
    ): io.appwrite.models.Target {
        val apiPath = "/account/targets/push"

        val apiParams = mutableMapOf<String, Any?>(
            "targetId" to targetId,
            "identifier" to identifier,
            "providerId" to providerId,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> io.appwrite.models.Target = {
            @Suppress("UNCHECKED_CAST")
            io.appwrite.models.Target.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = io.appwrite.models.Target::class,
            converter,
        )
    }

    /**
     * Create OAuth2 session
     *
     * Allow the user to login to their account using the OAuth2 provider of their choice. Each OAuth2 provider should be enabled from the Appwrite console first. Use the success and failure arguments to provide a redirect URL&#039;s back to your app when login is completed.If there is already an active session, the new session will be attached to the logged-in account. If there are no active sessions, the server will attempt to look for a user with the same email address as the email received from the OAuth2 provider and attach the new session to the existing user. If no matching user is found - the server will create a new user.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](https://appwrite.io/docs/authentication-security#limits).
     *
     * @param provider OAuth2 Provider. Currently, supported providers are: amazon, apple, auth0, authentik, autodesk, bitbucket, bitly, box, dailymotion, discord, disqus, dropbox, etsy, facebook, github, gitlab, google, linkedin, microsoft, notion, oidc, okta, paypal, paypalSandbox, podio, salesforce, slack, spotify, stripe, tradeshift, tradeshiftBox, twitch, wordpress, yahoo, yammer, yandex, zoho, zoom.
     * @param success URL to redirect back to your app after a successful login attempt.  Only URLs from hostnames in your project's platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @param failure URL to redirect back to your app after a failed login attempt.  Only URLs from hostnames in your project's platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @param scopes A list of custom OAuth2 scopes. Check each provider internal docs for a list of supported scopes. Maximum of 100 scopes are allowed, each 4096 characters long.
     */
    @JvmOverloads
    suspend fun createOAuth2Session(
        provider: OAuthProvider,
        success: String?,
        failure: String?,
        scopes: List<String>?
    ) {
        val apiPath = "/account/sessions/oauth2/{provider}"
            .replace("{provider}", provider.value)

        val apiParams = mutableMapOf<String, Any?>(
            "success" to success,
            "failure" to failure,
            "scopes" to scopes,
            "project" to client.config["project"],
        )
        val apiQuery = mutableListOf<String>()
        apiParams.forEach {
            when (it.value) {
                null -> return@forEach
                is List<*> -> apiQuery.add("${it.key}[]=${it.value.toString()}")
                else -> apiQuery.add("${it.key}=${it.value.toString()}")
            }
        }

        val urlParser = UrlParser()
        val fullUrl = "${client.endpoint}${apiPath}?${apiQuery.joinToString("&")}"
        val apiUrl = urlParser.parse(fullUrl)
        val callbackUrlScheme = "appwrite-callback-${client.config["project"]}"

        val webAuth = WebAuthComponent()
        webAuth.authenticate(apiUrl.toString(), callbackUrlScheme) {

            if (it.isFailure) {
                throw it.exceptionOrNull()!!
            }

            val resultUrl = it.getOrNull()!!
            val key = urlParser.getQueryParameter(resultUrl, "key")
            val secret = urlParser.getQueryParameter(resultUrl, "secret")
            if (key == null || secret == null) {
                throw AppwriteException("Authentication cookie missing!")
            }

            kotlinx.coroutines.runBlocking {
                val existingCookies = client.httpClient.cookies(client.endpoint)
                if (existingCookies.any { cookie: Cookie -> cookie.name == key }) {
                    return@runBlocking
                }

                client.httpClient.get(client.endpoint) {
                    cookie(
                        name = key,
                        value = secret,
                        domain = urlParser.getHost(client.endpoint),
                        path = "/",
                        httpOnly = true
                    )
                }
            }
        }
    }

    /**
     * Update push target
     *
     *
     *
     * @param targetId Target ID.
     * @param identifier The target identifier (token, email, phone etc.)
     * @return [Target]
     */
    suspend fun updatePushTarget(
        targetId: String,
        identifier: String,
    ): io.appwrite.models.Target {
        val apiPath = "/account/targets/{targetId}/push"
            .replace("{targetId}", targetId)

        val apiParams = mutableMapOf<String, Any?>(
            "identifier" to identifier,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> io.appwrite.models.Target = {
            @Suppress("UNCHECKED_CAST")
            io.appwrite.models.Target.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = io.appwrite.models.Target::class,
            converter,
        )
    }


    /**
     * Delete push target
     *
     *
     *
     * @param targetId Target ID.
     * @return [Any]
     */
    suspend fun deletePushTarget(
        targetId: String,
    ): Any {
        val apiPath = "/account/targets/{targetId}/push"
            .replace("{targetId}", targetId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class,
        )
    }


    /**
     * Create email token (OTP)
     *
     * Sends the user an email with a secret key for creating a session. If the provided user ID has not be registered, a new user will be created. Use the returned user ID and secret and submit a request to the [POST /v1/account/sessions/token](https://appwrite.io/docs/references/cloud/client-web/account#createSession) endpoint to complete the login process. The secret sent to the user&#039;s email is valid for 15 minutes.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](https://appwrite.io/docs/authentication-security#limits).
     *
     * @param userId User ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param phrase Toggle for security phrase. If enabled, email will be send with a randomly generated phrase and the phrase will also be included in the response. Confirming phrases match increases the security of your authentication flow.
     * @return [Token]
     */
    @JvmOverloads
    suspend fun createEmailToken(
        userId: String,
        email: String,
        phrase: Boolean? = null,
    ): Token {
        val apiPath = "/account/tokens/email"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "email" to email,
            "phrase" to phrase,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Create magic URL token
     *
     * Sends the user an email with a secret key for creating a session. If the provided user ID has not been registered, a new user will be created. When the user clicks the link in the email, the user is redirected back to the URL you provided with the secret key and userId values attached to the URL query string. Use the query string parameters to submit a request to the [POST /v1/account/sessions/token](https://appwrite.io/docs/references/cloud/client-web/account#createSession) endpoint to complete the login process. The link sent to the user&#039;s email address is valid for 1 hour. If you are on a mobile device you can leave the URL parameter empty, so that the login completion will be handled by your Appwrite instance by default.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](https://appwrite.io/docs/authentication-security#limits).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param email User email.
     * @param url URL to redirect the user back to your app from the magic URL login. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @param phrase Toggle for security phrase. If enabled, email will be send with a randomly generated phrase and the phrase will also be included in the response. Confirming phrases match increases the security of your authentication flow.
     * @return [Token]
     */
    @JvmOverloads
    suspend fun createMagicURLToken(
        userId: String,
        email: String,
        url: String? = null,
        phrase: Boolean? = null,
    ): Token {
        val apiPath = "/account/tokens/magic-url"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "email" to email,
            "url" to url,
            "phrase" to phrase,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }

    @JvmOverloads
    suspend fun createOAuth2Token(
        provider: OAuthProvider,
        success: String?,
        failure: String?,
        scopes: List<String>?
    ) {
        val apiPath = "/account/tokens/oauth2/{provider}"
            .replace("{provider}", provider.value)

        val apiParams = mutableMapOf<String, Any?>(
            "success" to success,
            "failure" to failure,
            "scopes" to scopes,
            "project" to client.config["project"],
        )
        val apiQuery = mutableListOf<String>()
        apiParams.forEach {
            when (it.value) {
                null -> return@forEach
                is List<*> -> apiQuery.add("${it.key}[]=${it.value.toString()}")
                else -> apiQuery.add("${it.key}=${it.value.toString()}")
            }
        }

        val urlParser = UrlParser()
        val fullUrl = "${client.endpoint}${apiPath}?${apiQuery.joinToString("&")}"
        val apiUrl = urlParser.parse(fullUrl)
        val callbackUrlScheme = "appwrite-callback-${client.config["project"]}"

        val webAuth = WebAuthComponent()
        webAuth.authenticate(apiUrl.toString(), callbackUrlScheme) {
            if (it.isFailure) {
                throw it.exceptionOrNull()!!
            }

            val resultUrl = it.getOrNull()!!
            val key = urlParser.getQueryParameter(resultUrl, "key")
            val secret = urlParser.getQueryParameter(resultUrl, "secret")
            if (key == null || secret == null) {
                throw AppwriteException("Authentication cookie missing!")
            }

            kotlinx.coroutines.runBlocking {
                val existingCookies = client.httpClient.cookies(client.endpoint)
                if (existingCookies.any { cookie: Cookie -> cookie.name == key }) {
                    return@runBlocking
                }

                client.httpClient.get(client.endpoint) {
                    cookie(
                        name = key,
                        value = secret,
                        domain = urlParser.getHost(client.endpoint),
                        path = "/",
                        httpOnly = true
                    )
                }
            }
        }
    }


    /**
     * Create phone token
     *
     * Sends the user an SMS with a secret key for creating a session. If the provided user ID has not be registered, a new user will be created. Use the returned user ID and secret and submit a request to the [POST /v1/account/sessions/token](https://appwrite.io/docs/references/cloud/client-web/account#createSession) endpoint to complete the login process. The secret sent to the user&#039;s phone is valid for 15 minutes.A user is limited to 10 active sessions at a time by default. [Learn more about session limits](https://appwrite.io/docs/authentication-security#limits).
     *
     * @param userId Unique Id. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param phone Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @return [Token]
     */
    suspend fun createPhoneToken(
        userId: String,
        phone: String,
    ): Token {
        val apiPath = "/account/tokens/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "phone" to phone,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Create email verification
     *
     * Use this endpoint to send a verification message to your user email address to confirm they are the valid owners of that address. Both the **userId** and **secret** arguments will be passed as query parameters to the URL you have provided to be attached to the verification email. The provided URL should redirect the user back to your app and allow you to complete the verification process by verifying both the **userId** and **secret** parameters. Learn more about how to [complete the verification process](https://appwrite.io/docs/references/cloud/client-web/account#updateVerification). The verification link sent to the user&#039;s email address is valid for 7 days.Please note that in order to avoid a [Redirect Attack](https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.md), the only valid redirect URLs are the ones from domains you have set when adding your platforms in the console interface.
     *
     * @param url URL to redirect the user back to your app from the verification email. Only URLs from hostnames in your project platform list are allowed. This requirement helps to prevent an [open redirect](https://cheatsheetseries.owasp.org/cheatsheets/Unvalidated_Redirects_and_Forwards_Cheat_Sheet.html) attack against your project API.
     * @return [Token]
     */
    suspend fun createVerification(
        url: String,
    ): Token {
        val apiPath = "/account/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "url" to url,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Create email verification (confirmation)
     *
     * Use this endpoint to complete the user email verification process. Use both the **userId** and **secret** parameters that were attached to your app URL to verify the user email ownership. If confirmed this route will return a 200 status code.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [Token]
     */
    suspend fun updateVerification(
        userId: String,
        secret: String,
    ): Token {
        val apiPath = "/account/verification"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Create phone verification
     *
     * Use this endpoint to send a verification SMS to the currently logged in user. This endpoint is meant for use after updating a user&#039;s phone number using the [accountUpdatePhone](https://appwrite.io/docs/references/cloud/client-web/account#updatePhone) endpoint. Learn more about how to [complete the verification process](https://appwrite.io/docs/references/cloud/client-web/account#updatePhoneVerification). The verification code sent to the user&#039;s phone number is valid for 15 minutes.
     *
     * @return [Token]
     */
    suspend fun createPhoneVerification(
    ): Token {
        val apiPath = "/account/verification/phone"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


    /**
     * Update phone verification (confirmation)
     *
     * Use this endpoint to complete the user phone verification process. Use the **userId** and **secret** that were sent to your user&#039;s phone number to verify the user email ownership. If confirmed this route will return a 200 status code.
     *
     * @param userId User ID.
     * @param secret Valid verification token.
     * @return [Token]
     */
    suspend fun updatePhoneVerification(
        userId: String,
        secret: String,
    ): Token {
        val apiPath = "/account/verification/phone"

        val apiParams = mutableMapOf<String, Any?>(
            "userId" to userId,
            "secret" to secret,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Token = {
            @Suppress("UNCHECKED_CAST")
            Token.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Token::class,
            converter,
        )
    }


}