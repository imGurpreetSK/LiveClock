package com.gurpreetsk.liveclock.ui.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TimeElementTest {

    @Test
    fun `denote that an element is separator if value is null`() {
        val element = TimeElement(19832, null)
        assertThat(element.isSeparator).isTrue()
    }

    @Test
    fun `denote that an element is a time value if value is not null`() {
        val element = TimeElement(5, 17)
        assertThat(element.isSeparator).isFalse()
    }

    @ParameterizedTest
    @MethodSource("pseudoConsistentIdSource")
    fun `pseudo consistent ID should be deterministic for same inputs`(testParams: Triple<Int, Char, Int>) {
        assertThat(TimeElement.getPseudoConsistentId(testParams.first, testParams.second))
            .isEqualTo(testParams.third)
    }

    private companion object {
        @JvmStatic
        fun pseudoConsistentIdSource(): Stream<Triple<Int, Char, Int>> = listOf(
            Triple(0, '1', 49),
            Triple(12, '5', 197),
            Triple(8, '7', 119),
        ).stream()
    }
}
