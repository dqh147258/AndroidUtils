package com.yxf.androidutil.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> Flow<T>.collectOnCoroutine(
    coroutineScope: CoroutineScope,
    block: (T) -> Unit,
    coroutineContext: CoroutineContext
) {
    val flow = this
    coroutineScope.launch(coroutineContext) {
        flow.collect { block(it) }
    }
}

fun <T> Flow<T>.collectOnCoroutine(coroutineScope: CoroutineScope, block: (T) -> Unit) {
    collectOnCoroutine(coroutineScope, block, Dispatchers.Main)
}

fun <T> Flow<T>.catchOnCoroutine(
    coroutineScope: CoroutineScope,
    block: FlowCollector<T>.(Throwable) -> Unit,
    coroutineContext: CoroutineContext
): Flow<T> {
    return catch {
        val e = it
        val fc = this
        coroutineScope.launch(coroutineContext) {
            block(e)
        }
    }
}

fun <T> Flow<T>.catchOnCoroutine(
    coroutineScope: CoroutineScope,
    block: FlowCollector<T>.(Throwable) -> Unit
): Flow<T> {
    return catchOnCoroutine(coroutineScope, block, Dispatchers.Main)
}

fun CoroutineScope.launch(
    context: CoroutineContext,
    exceptionHandler: CoroutineExceptionHandler,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch(exceptionHandler) {
        launch(context, start, block)
    }
}