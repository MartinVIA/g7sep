import java.time.LocalDate;

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

    public int getFullDate() {
        int fullDate = (year * 365) + (month * 30) + day;
        return fullDate;
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

    public boolean isBefore(Date date2) {
        int totalDate1 = 0,
                totalDate2 = 0;

        for (int i = 0; i < this.year; i++) {
            totalDate1 += isLeapYear() ? 366 : 365;
        }

        for (int i = 1; i < this.month; i++) {
            totalDate1 += daysInMonth();
        }
        totalDate1 += this.day;

        for (int i = 0; i < date2.year; i++) {
            totalDate2 += isLeapYear() ? 366 : 365;
        }

        for (int i = 1; i < date2.month; i++) {
            totalDate2 += daysInMonth();
        }
        totalDate2 += date2.day;

        return totalDate1 < totalDate2;
    }

    public boolean isLeapYear() {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                else
                    return false;
            }
            return true;
        }
        return false;
    }

    public int daysInMonth() {
        switch (this.month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return 31;
            }
            case 2 -> {
                return isLeapYear() ? 29 : 28;
            }
            case 4, 6, 9, 11 -> {
                return 30;
            }
        }
        return 0;
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

}