package base;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents the days of the week.
 */
public record Day(int day) {

  private static final Logger logger = LoggerFactory.getLogger("base.Day");

  public static final int MONDAY = 1;
  public static final int TUESDAY = 2;
  public static final int WEDNESDAY = 3;
  public static final int THURSDAY = 4;
  public static final int FRIDAY = 5;
  public static final int SATURDAY = 6;
  public static final int SUNDAY = 7;

  /**
   * Get the days of the week, from Monday to Friday.
   *
   * @return A list of the days of the week
   */
  public static ArrayList<Day> getWeekDays() {
    logger.info("Fetching weekdays (Monday to Friday).");
    ArrayList<Day> weekDays = new ArrayList<>();
    weekDays.add(new Day(MONDAY));
    weekDays.add(new Day(TUESDAY));
    weekDays.add(new Day(WEDNESDAY));
    weekDays.add(new Day(THURSDAY));
    weekDays.add(new Day(FRIDAY));
    logger.debug("Weekdays initialized: {}", weekDays);
    return weekDays;
  }

  /**
   * Get the days of the weekend, Saturday and Sunday.
   *
   * @return A list of the days of the weekend
   */
  public static ArrayList<Day> getWeekendDays() {
    logger.info("Fetching weekend days (Saturday and Sunday).");
    ArrayList<Day> weekendDays = new ArrayList<>();
    weekendDays.add(new Day(SATURDAY));
    weekendDays.add(new Day(SUNDAY));
    logger.debug("Weekend days initialized: {}", weekendDays);
    return weekendDays;
  }

  /**
   * Get all the days of the week.
   *
   * @return A list of all the days of the week
   */
  public static ArrayList<Day> getAllDays() {
    logger.info("Fetching all days of the week.");
    ArrayList<Day> allDays = new ArrayList<>();
    allDays.addAll(getWeekDays());
    allDays.addAll(getWeekendDays());
    logger.debug("All days of the week initialized: {}", allDays);
    return allDays;
  }

  /**
   * Get the day of the week.
   *
   * @return The day of the week
   */
  @Override
  public int day() {
    logger.info("Accessing the day value: {}", day);
    return day;
  }
}
