package io.appwrite.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * MFA Recovery Codes
 */
@Serializable
data class MfaRecoveryCodes(
    /**
     * Recovery codes.
     */
    @SerialName("recoveryCodes")
    val recoveryCodes: List<@Contextual Any>,

    )