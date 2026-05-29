package dev.makethiswork.criticaltechnews.core.presentation.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.ZoneId
import java.util.Locale

class DateFormatterTest {

    private val zone = ZoneId.of("UTC")
    private val now = Instant.parse("2026-05-29T12:00:00Z")
    private val locale = Locale.ENGLISH

    @Test
    fun `dayBucket labels same day as Today`() {
        val instant = Instant.parse("2026-05-29T06:00:00Z")
        assertEquals("Today", DateFormatter.dayBucket(instant, now, zone))
    }

    @Test
    fun `dayBucket labels previous day as Yesterday`() {
        val instant = Instant.parse("2026-05-28T23:00:00Z")
        assertEquals("Yesterday", DateFormatter.dayBucket(instant, now, zone))
    }

    @Test
    fun `dayBucket labels older dates with weekday day month`() {
        val instant = Instant.parse("2026-05-26T10:00:00Z")
        assertEquals("Tuesday 26 May", DateFormatter.dayBucket(instant, now, zone, locale))
    }

    @Test
    fun `timeAgo under a minute is Just now`() {
        val instant = Instant.parse("2026-05-29T11:59:30Z")
        assertEquals("Just now", DateFormatter.timeAgo(instant, now, zone))
    }

    @Test
    fun `timeAgo within the hour shows minutes`() {
        val instant = Instant.parse("2026-05-29T11:25:00Z")
        assertEquals("35m ago", DateFormatter.timeAgo(instant, now, zone))
    }

    @Test
    fun `timeAgo within the day shows hours`() {
        val instant = Instant.parse("2026-05-29T09:00:00Z")
        assertEquals("3h ago", DateFormatter.timeAgo(instant, now, zone))
    }

    @Test
    fun `timeAgo within the week shows days`() {
        val instant = Instant.parse("2026-05-27T12:00:00Z")
        assertEquals("2d ago", DateFormatter.timeAgo(instant, now, zone))
    }

    @Test
    fun `timeAgo beyond a week falls back to a date`() {
        val instant = Instant.parse("2026-05-01T12:00:00Z")
        assertEquals("Friday 1 May", DateFormatter.timeAgo(instant, now, zone, locale))
    }
}
