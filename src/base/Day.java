package base;

import java.util.ArrayList;

/**
 * A class that represents the days of the week.
 */
public record Day(int day) {

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
    ArrayList<Day> weekDays = new ArrayList<>();
    weekDays.add(new Day(MONDAY));
    weekDays.add(new Day(TUESDAY));
    weekDays.add(new Day(WEDNESDAY));
    weekDays.add(new Day(THURSDAY));
    weekDays.add(new Day(FRIDAY));
    return weekDays;
  }

  /**
   * Get the days of the weekend, Saturday and Sunday.
   *
   * @return A list of the days of the weekend
   */
  public static ArrayList<Day> getWeekendDays() {
    ArrayList<Day> weekendDays = new ArrayList<>();
    weekendDays.add(new Day(SATURDAY));
    weekendDays.add(new Day(SUNDAY));
    return weekendDays;
  }

  /**
   * Get all the days of the week.
   *
   * @return A list of all the days of the week
   */
  public static ArrayList<Day> getAllDays() {
    ArrayList<Day> allDays = new ArrayList<>();
    allDays.addAll(getWeekDays());
    allDays.addAll(getWeekendDays());
    return allDays;
  }

  /**
   * Get the day of the week.
   *
   * @return The day of the week
   */
  @Override
  public int day() {
    return day;
  }
}
