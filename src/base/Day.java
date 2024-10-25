package base;

import java.util.ArrayList;

public final class Day {

    public final static int MONDAY = 1;
    public final static int TUESDAY = 2;
    public final static int WEDNESDAY = 3;
    public final static int THURSDAY = 4;
    public final static int FRIDAY = 5;
    public final static int SATURDAY = 6;
    public final static int SUNDAY = 7;

    private final int day;

    /**
     * Day constructor.
     *
     * @param day The day of the week
     */
    public Day(int day) {
        // Set the day of the week
        this.day = day;
    }

    /**
     * Get the days of the week, from Monday to Friday.
     *
     * @return A list of the days of the week
     */
    public static ArrayList<Day> getWeekDays() {
        ArrayList<Day> weekDays = new ArrayList<Day>();
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
        ArrayList<Day> weekendDays = new ArrayList<Day>();
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
        ArrayList<Day> allDays = new ArrayList<Day>();
        allDays.add(new Day(MONDAY));
        allDays.add(new Day(TUESDAY));
        allDays.add(new Day(WEDNESDAY));
        allDays.add(new Day(THURSDAY));
        allDays.add(new Day(FRIDAY));
        allDays.add(new Day(SATURDAY));
        allDays.add(new Day(SUNDAY));
        return allDays;
    }

    /**
     * Get the day number
     *
     * @return The week day number
     */
    public int getDay() {
        return day;
    }
}
