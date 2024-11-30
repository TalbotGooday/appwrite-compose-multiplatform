package io.appwrite.models

import Execution
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Executions List
 */
@Serializable
data class ExecutionList(
    /**
     * Total number of executions documents that matched your query.
     */
    @SerialName("total")
    val total: Long,

    /**
     * List of executions.
     */
    @SerialName("executions")
    val executions: List<Execution>,

)