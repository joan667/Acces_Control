package baseNoStates;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Schedule {

    private final LocalDate start;
    private final LocalDate end;
    private final ArrayList<DaySchedule> daySchedules;

    /**
     * Create an empty schedule from a date to another.
     *
     * @param start The start date
     * @param end The end date
     */
    public Schedule(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        this.daySchedules = new ArrayList<DaySchedule>();
    }

    /**
     * Create a schedule with a list of day schedules from a date to another.
     *
     * @param daySchedules The list of day schedules
     */
    public Schedule(LocalDate start, LocalDate end, ArrayList<DaySchedule> daySchedules) {
        this.start = start;
        this.end = end;
        this.daySchedules = daySchedules;
    }

    /**
     * Add a day schedule to the schedule.
     *
     * @param daySchedule The day schedule to add
     */
    public void add(DaySchedule daySchedule) {
        daySchedules.add(daySchedule);
    }

    /**
     * Check if a date is in the schedule.
     *
     * @param datetime The date and time to check
     * @return True if the date is in the schedule, false otherwise
     */
    public boolean isInSchedule(LocalDateTime datetime) {
        // Check if the date is before the start date
        if (datetime.toLocalDate().isBefore(start))
            return false;

        // Check if the date is after the end date
        if (datetime.toLocalDate().isAfter(end))
            return false;

        // Check if the date is in any day schedule
        for (DaySchedule daySchedule : daySchedules)
            if (daySchedule.isInSchedule(datetime))
                return true;
        return false;
    }
}
