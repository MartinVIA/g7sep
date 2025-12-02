package model;

import java.time.*;

public class Date {
    private int day, month, year;

    LocalDate currentDate = LocalDate.now();
    private int currDay = currentDate.getDayOfMonth();
    private int currMonth = currentDate.getMonthValue();
    private int currYear = currentDate.getYear();

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public long getNumOfDays() {
        // int fullDate = (year * 365) + (month * 30) + day;
        // return fullDate;
        int year = getYear();
        int month = getMonth();
        int day = getDay();
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate.toEpochDay(); // days since 1970-01-01
    }

    public Date copy() {
        return new Date(day, month, year);
    }

    public Date today() {
        LocalDate currentDate = LocalDate.now();
        return new Date(currentDate.getDayOfMonth(),
                currentDate.getMonthValue(),
                currentDate.getYear());
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        Date other = (Date) obj;
        return this.day == other.day &&
                this.month == other.month &&
                this.year == other.year;
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

}