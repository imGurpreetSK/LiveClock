package com.gurpreetsk.liveclock

import com.gurpreetsk.liveclock.ui.model.TimeElement
import com.gurpreetsk.liveclock.ui.model.TimeElement.Companion.getPseudoConsistentId
import com.gurpreetsk.liveclock.utils.Clock
import com.gurpreetsk.liveclock.utils.FakeSchedulersProvider
import org.junit.jupiter.api.Test
import java.time.LocalTime

class TimeTickerTest {

    @Test
    fun `observe formatted time updates`() {
        // Setup
        val fakeClock = FakeClock()
        val ticker = TimeTicker(fakeClock, FakeSchedulersProvider)

        // Act
        val observer = ticker.observe().take(3).test()

        // Assert
        observer
            .assertNoErrors()
            .assertComplete()
            .assertValues(
                listOf(
                    TimeElement(getPseudoConsistentId(0, '1'), 1),
                    TimeElement(getPseudoConsistentId(1, '3'), 3),
                    TimeElement(getPseudoConsistentId(2, ':'), null),
                    TimeElement(getPseudoConsistentId(3, '0'), 0),
                    TimeElement(getPseudoConsistentId(4, '3'), 3),
                    TimeElement(getPseudoConsistentId(5, ':'), null),
                    TimeElement(getPseudoConsistentId(6, '0'), 0),
                    TimeElement(getPseudoConsistentId(7, '5'), 5),
                ),
                listOf(
                    TimeElement(getPseudoConsistentId(0, '0'), 0),
                    TimeElement(getPseudoConsistentId(1, '0'), 0),
                    TimeElement(getPseudoConsistentId(2, ':'), null),
                    TimeElement(getPseudoConsistentId(3, '0'), 0),
                    TimeElement(getPseudoConsistentId(4, '0'), 0),
                    TimeElement(getPseudoConsistentId(5, ':'), null),
                    TimeElement(getPseudoConsistentId(6, '0'), 0),
                    TimeElement(getPseudoConsistentId(7, '0'), 0),
                ),
                listOf(
                    TimeElement(getPseudoConsistentId(0, '2'), 2),
                    TimeElement(getPseudoConsistentId(1, '3'), 3),
                    TimeElement(getPseudoConsistentId(2, ':'), null),
                    TimeElement(getPseudoConsistentId(3, '5'), 5),
                    TimeElement(getPseudoConsistentId(4, '9'), 9),
                    TimeElement(getPseudoConsistentId(5, ':'), null),
                    TimeElement(getPseudoConsistentId(6, '5'), 5),
                    TimeElement(getPseudoConsistentId(7, '9'), 9),
                ),
            )
    }
}

private class FakeClock : Clock<LocalTime> {

    private var invocationTime = 0

    override fun now(): LocalTime {
        return when (invocationTime % 3) {
            0 -> LocalTime.of(13, 3, 5)
            1 -> LocalTime.of(0, 0, 0)
            2 -> LocalTime.of(23, 59, 59)

            else -> LocalTime.of(7, 7, 7)
        }.also { invocationTime++ }
    }
}
