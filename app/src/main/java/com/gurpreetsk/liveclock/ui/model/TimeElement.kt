package com.gurpreetsk.liveclock.ui.model

data class TimeElement(
    val id: Int,
    val value: Int?
) {
    val isSeparator = value == null

    companion object {
        fun getPseudoConsistentId(index: Int, char: Char) = index * index + char.code
    }
}
