package baseNoStates;

import java.util.ArrayList;

public final class Day {

    public final static int Monday = 1;
    public final static int Tuesday = 2;
    public final static int Wednesday = 3;
    public final static int Thursday = 4;
    public final static int Friday = 5;
    public final static int Saturday = 6;
    public final static int Sunday = 7;

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
        weekDays.add(new Day(Monday));
        weekDays.add(new Day(Tuesday));
        weekDays.add(new Day(Wednesday));
        weekDays.add(new Day(Thursday));
        weekDays.add(new Day(Friday));
        return weekDays;
    }

    /**
     * Get the days of the weekend, Saturday and Sunday.
     *
     * @return A list of the days of the weekend
     */
    public static ArrayList<Day> getWeekendDays() {
        ArrayList<Day> weekendDays = new ArrayList<Day>();
        weekendDays.add(new Day(Saturday));
        weekendDays.add(new Day(Sunday));
        return weekendDays;
    }

    /**
     * Get all the days of the week.
     *
     * @return A list of all the days of the week
     */
    public static ArrayList<Day> getAllDays() {
        ArrayList<Day> allDays = new ArrayList<Day>();
        allDays.add(new Day(Monday));
        allDays.add(new Day(Tuesday));
        allDays.add(new Day(Wednesday));
        allDays.add(new Day(Thursday));
        allDays.add(new Day(Friday));
        allDays.add(new Day(Saturday));
        allDays.add(new Day(Sunday));
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
