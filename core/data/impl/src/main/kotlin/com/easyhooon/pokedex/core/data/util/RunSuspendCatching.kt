package com.easyhooon.pokedex.core.data.util

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException
import kotlin.contracts.ExperimentalContracts

@OptIn(ExperimentalContracts::class)
@Suppress("WRONG_INVOCATION_KIND", "TooGenericExceptionCaught")
inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return try {
        Result.success(block())
    } catch (rethrown: CancellationException) {
        throw rethrown
    } catch (exception: Throwable) {
        Result.failure(exception)
    }
}
