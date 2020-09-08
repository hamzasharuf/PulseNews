package com.hamzasharuf.pulse.utils

import java.util.*


object DateFormatter {

    private const val IN_THE_FUTURE = "At the future"
    private const val MOMENTS_AGO = "Moments ago"
    private const val A_MINUTE_AGO = "A minute ago"
    private const val MINUTES_AGO = "minutes ago"
    private const val AN_HOUR_AGO = "An hour ago"
    private const val HOURS_AGO = "hours ago"
    private const val YESTERDAY = "Yesterday"
    private const val DAYS_AGO = "days ago"
    private const val A_WEEK_AGO = "A week ago"
    private const val WEEKS_AGO = "weeks ago"
    private const val A_MONTH_AGO = "A month ago"
    private const val MONTHS_AGO = "months ago"
    private const val A_YEAR_AGO = "A year ago"
    private const val YEARS_AGO = "years ago"

    private const val SECOND_MILLIS: Long = 1000
    private const val MINUTE_MILLIS: Long = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS: Long = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS: Long = 24 * HOUR_MILLIS
    private const val WEEK_MILLIS: Long = 7 * DAY_MILLIS
    private const val MONTH_MILLIS: Long = 4 * WEEK_MILLIS
    private const val YEAR_MILLIS: Long = 12 * MONTH_MILLIS

    fun getRelativeDate(date: Date): String {

        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = currentDate().time
        if (time > now || time <= 0) {
            return IN_THE_FUTURE
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> MOMENTS_AGO
            diff < 2 * MINUTE_MILLIS -> A_MINUTE_AGO
            diff < HOUR_MILLIS -> "${diff / MINUTE_MILLIS} $MINUTES_AGO"
            diff < 2 * HOUR_MILLIS -> AN_HOUR_AGO
            diff < DAY_MILLIS -> "${diff / HOUR_MILLIS} $HOURS_AGO"
            diff < 2 * DAY_MILLIS -> YESTERDAY
            diff < 7 * DAY_MILLIS -> "${diff / DAY_MILLIS} $DAYS_AGO"
            diff < 2 * WEEK_MILLIS -> A_WEEK_AGO
            diff < 4 * WEEK_MILLIS -> "${diff / WEEK_MILLIS} $WEEKS_AGO"
            diff < 2 * MONTH_MILLIS -> A_MONTH_AGO
            diff < 12 * MONTH_MILLIS -> "${diff / MONTH_MILLIS} $MONTHS_AGO"
            diff < 2 * YEAR_MILLIS -> A_YEAR_AGO
            else -> "${diff / YEAR_MILLIS} $YEARS_AGO"
        }
    }

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

}