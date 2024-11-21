package base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that represents a schedule.
 */
public class Schedule {

  private static final Logger logger = LoggerFactory.getLogger("base.Schedule");

  private final LocalDate start;
  private final LocalDate end;
  private final ArrayList<DaySchedule> daySchedules;

  /**
   * Create an empty schedule from a date to another.
   *
   * @param start The start date
   * @param end   The end date
   */
  public Schedule(LocalDate start, LocalDate end) {
    logger.info("Creating an empty schedule from {} to {}", start, end);
    this.start = start;
    this.end = end;
    this.daySchedules = new ArrayList<>();
    logger.info("Empty schedule created successfully.");
  }

  /**
   * Create a schedule with a list of day schedules from a date to another.
   *
   * @param start        The start date
   * @param end          The end date
   * @param daySchedules The list of day schedules
   */
  public Schedule(LocalDate start, LocalDate end, ArrayList<DaySchedule> daySchedules) {
    logger.info("Creating a schedule from {} to {} with predefined day schedules.", start, end);
    this.start = start;
    this.end = end;
    this.daySchedules = daySchedules;
    logger.info("Schedule with day schedules created successfully.");
  }

  /**
   * Add a day schedule to the schedule.
   *
   * @param daySchedule The day schedule to add
   */
  public void add(DaySchedule daySchedule) {
    logger.debug("Adding day schedule for day: {}", daySchedule);
    daySchedules.add(daySchedule);
    logger.info("Day schedule added successfully.");
  }

  /**
   * Check if a date is in the schedule.
   *
   * @param datetime The date and time to check
   * @return True if the date is in the schedule, false otherwise
   */
  public boolean isInSchedule(LocalDateTime datetime) {
    logger.debug("Checking if datetime {} is in schedule from {} to {}", datetime, start, end);

    if (datetime.toLocalDate().isBefore(start)) {
      logger.info("Datetime {} is before the start date of the schedule.", datetime);
      return false;
    }

    if (datetime.toLocalDate().isAfter(end)) {
      logger.info("Datetime {} is after the end date of the schedule.", datetime);
      return false;
    }

    for (DaySchedule daySchedule : daySchedules) {
      if (daySchedule.isInSchedule(datetime)) {
        logger.info("Datetime {} is in the schedule for day schedule: {}", datetime, daySchedule);
        return true;
      }
    }

    logger.info("Datetime {} is not in the schedule.", datetime);
    return false;
  }
}
