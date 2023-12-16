package com.gurpreetsk.liveclock

import com.gurpreetsk.liveclock.ui.model.TimeElement
import com.gurpreetsk.liveclock.ui.model.TimeElement.Companion.getPseudoConsistentId
import com.gurpreetsk.liveclock.utils.Clock
import com.gurpreetsk.liveclock.utils.SchedulersProvider
import io.reactivex.rxjava3.core.Flowable
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

internal class TimeTicker(
    private val clock: Clock<LocalTime>,
    private val schedulersProvider: SchedulersProvider
) {

    fun observe(): Flowable<List<TimeElement>> = Flowable
        .interval(0, TimeUnit.SECONDS, schedulersProvider.io())
        .onBackpressureDrop()
        .subscribeOn(schedulersProvider.io())
        .map {
            clock.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                .mapIndexed { index, char ->
                    TimeElement(getPseudoConsistentId(index, char), char.digitToIntOrNull())
                }
        }
        .observeOn(schedulersProvider.io())
}
