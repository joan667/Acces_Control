package base;

import java.time.LocalDateTime;

public class DaySchedule {

    private final Day day;
    private final int start;
    private final int end;

    /**
     * Create a new day schedule.
     *
     * @param day The day of the week (Monday, Tuesday...)
     * @param start The start time of the schedule (1 to 23)
     * @param end The end time of the schedule (2 to 24)
     */
    public DaySchedule(Day day, int start, int end) {
        // Check if the start time is before the end time
        if (start >= end)
            throw new IllegalArgumentException("Start time must be before end time");

        // Check if the start time is between 1 and 23
        if (start < 1 || start > 23)
            throw new IllegalArgumentException("Start time must be between 1 and 23");

        // Check if the end time is between 2 and 24
        if (end < 2 || end > 24)
            throw new IllegalArgumentException("End time must be between 2 and 24");

        // Set the day of the week
        this.day = day;

        // Set the start and end time
        this.start = start;
        this.end = end;
    }

    /**
     * Check if a date is in the schedule.
     *
     * @param datetime The date and time to check
     * @return True if the date is in the schedule, false otherwise
     */
    public boolean isInSchedule(LocalDateTime datetime) {
        // Check if the date is on the same day
        if (datetime.getDayOfWeek().getValue() != day.getDay())
            return false;

        // Get the current time in seconds
        int time = datetime.getHour() * 3600 + datetime.getMinute() * 60 + datetime.getSecond();

        // Convert the start and end time to seconds
        int start = this.start * 3600;
        int end = this.end * 3600;

        // Check if the time is between the start and end time
        return time >= start && time < end;
    }

    /**
     * Get the day of the week.
     *
     * @return The day of the week
     */
    public Day getDay() {
        return day;
    }

    /**
     * Get the start time of the schedule.
     *
     * @return The start time of the schedule
     */
    public int getStart() {
        return start;
    }

    /**
     * Get the end time of the schedule.
     *
     * @return The end time of the schedule
     */
    public int getEnd() {
        return end;
    }
}
