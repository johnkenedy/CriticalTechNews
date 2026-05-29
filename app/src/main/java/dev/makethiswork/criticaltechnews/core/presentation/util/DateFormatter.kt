package dev.makethiswork.criticaltechnews.core.presentation.util

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    private const val DAY_LABEL_PATTERN = "EEEE d MMM"

    fun dayBucket(
        instant: Instant,
        now: Instant,
        zoneId: ZoneId = ZoneId.systemDefault(),
        locale: Locale = Locale.getDefault(),
    ): String {
        val date = instant.atZone(zoneId).toLocalDate()
        val today = now.atZone(zoneId).toLocalDate()
        return when (date) {
            today -> "Today"
            today.minusDays(1) -> "Yesterday"
            else -> date.format(dayLabelFormatter(locale))
        }
    }

    fun timeAgo(
        instant: Instant,
        now: Instant,
        zoneId: ZoneId = ZoneId.systemDefault(),
        locale: Locale = Locale.getDefault(),
    ): String {
        val elapsed = Duration.between(instant, now)
        val minutes = elapsed.toMinutes()
        val hours = elapsed.toHours()
        val days = elapsed.toDays()
        return when {
            elapsed.isNegative -> "Just now"
            minutes < 1 -> "Just now"
            minutes < 60 -> "${minutes}m ago"
            hours < 24 -> "${hours}h ago"
            days < 7 -> "${days}d ago"
            else -> instant.atZone(zoneId).toLocalDate().format(dayLabelFormatter(locale))
        }
    }

    private fun dayLabelFormatter(locale: Locale): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DAY_LABEL_PATTERN, locale)
}
