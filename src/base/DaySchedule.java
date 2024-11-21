package base;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a schedule for a day of the week.
 */
public class DaySchedule {

  private static final Logger logger = LoggerFactory.getLogger("base.DaySchedule");

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
    logger.info("Creating a new DaySchedule for day: {}, start: {}, end: {}", day, start, end);

    if (start >= end) {
      logger.error("Start time ({}) is not before end time ({}).", start, end);
      throw new IllegalArgumentException("Start time must be before end time");
    }

    if (start < 1 || start > 23) {
      logger.error("Start time ({}) is not between 1 and 23.", start);
      throw new IllegalArgumentException("Start time must be between 1 and 23");
    }

    if (end > 24) {
      logger.error("End time ({}) is not between 2 and 24.", end);
      throw new IllegalArgumentException("End time must be between 2 and 24");
    }

    this.day = day;
    this.start = start;
    this.end = end;
    logger.info("DaySchedule created successfully for day: {}, start: {}, end: {}",
        day, start, end);
  }

  /**
   * Check if a date is in the schedule.
   *
   * @param datetime The date and time to check
   * @return True if the date is in the schedule, false otherwise
   */
  public boolean isInSchedule(LocalDateTime datetime) {
    logger.debug("Checking if datetime: {} is in schedule for day: {}, start: {}, end: {}",
        datetime, day, start, end);

    if (datetime.getDayOfWeek().getValue() != day.day()) {
      logger.info("Datetime: {} is not on the same day as schedule day: {}", datetime, day);
      return false;
    }

    int time = datetime.getHour() * 3600 + datetime.getMinute() * 60 + datetime.getSecond();
    int start = this.start * 3600;
    int end = this.end * 3600;

    boolean inSchedule = time >= start && time < end;
    logger.info("Datetime: {} is {}in schedule for day: {}, start: {}, end: {}",
        datetime, inSchedule ? "" : "not ", day, start, end);

    return inSchedule;
  }
}
