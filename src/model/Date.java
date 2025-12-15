package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.*;

/**
 * Date representation wrapping year, month, and day with helpers
 * for comparisons, calculations and formatting
 */
public class Date implements Serializable {
  private int day, month, year;

  LocalDate currentDate = LocalDate.now();
  private int currDay = currentDate.getDayOfMonth();
  private int currMonth = currentDate.getMonthValue();
  private int currYear = currentDate.getYear();

  /**
   * Creates a date set to the current system date
   */
  public Date() {
    this.day = currDay;
    this.month = currMonth;
    this.year = currYear;
  }

  /**
   * Creates a specific date
   * 
   * @param day day
   * @param month month
   * @param year year
   */
  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Returns the day of the month
   * 
   * @return day
   */
  public int getDay() {
    return day;
  }

  /**
   * Returns the month of the year
   * 
   * @return month
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the year
   * 
   * @return year
   */
  public int getYear() {
    return year;
  }

  /**
   * Converts the date to an epoch-day count for comparisons
   * 
   * @return days since 1970-01-01
   */
  public long getNumOfDays() {
    int year = getYear();
    int month = getMonth();
    int day = getDay();
    LocalDate localDate = LocalDate.of(year, month, day);
    return localDate.toEpochDay();
  }

  
  /**
   * A method that creates a copy of the current date instance
   * 
   * @return date copy
   */
  public Date copy() {
    return new Date(day, month, year);
  }

  /**
   * Returns a new Date set to the current system date
   * 
   * @return date instance for today
   */
  public Date today() {
    LocalDate currentDate = LocalDate.now();
    return new Date(currentDate.getDayOfMonth(),
        currentDate.getMonthValue(),
        currentDate.getYear());
  }
  
  /**
     * Compares a date with another object
     * 
     * @param obj Object to compare
     * @return true if the objects are equal, false otherwise
     */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    Date other = (Date) obj;
    return this.day == other.day &&
        this.month == other.month &&
        this.year == other.year;
  }

  /**
   * Returns a string representation of the date in DD/MM/YYYY format
   * 
   * @return a formatted String with day, month and year
   */
  public String toString() {
    // return String.format("%02d/%02d/%04d", day, month, year);
    DecimalFormat dmFormat = new DecimalFormat("00");
    DecimalFormat yFormat = new DecimalFormat("0000");
    return dmFormat.format(getDay()) + "/" + dmFormat.format(getMonth()) + "/" + yFormat.format(getYear());
  }

}