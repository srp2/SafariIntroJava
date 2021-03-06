package datestuff;

import javax.naming.ServiceUnavailableException;

public class Date {
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public int getDay() {
    return this.day;
  }

  public static boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
  }

  public static int daysInMonth(int month, int year)
      throws ServiceUnavailableException {
    if (month < 1 || month > 12) {
      throw new ServiceUnavailableException("Bad Date");
    }
    switch (month) { // int, String, "enum" but not "general types"
      case 9: case 4:
//        System.out.println("hit four!!!");
//        break;/// DON'T FORGET!!!
      case 6: case 11:
        return 30;
//      System.out.println("never executed");
      case 2: return isLeapYear(year) ? 29 : 28;
      default: return 31;
    }
  }

  // Zeller's Congruence
  // 0 -> Saturday
  // Java does not have named argument passing (only positional)
  // Also, no "default" args
  public static int dayOfWeek(int day, int month, int year) {
    int m = month;
    int y = year;
    if (month < 3) {
      m += 12;
      y -= 1;
    }
    return (day + (13 * (m + 1) / 5) + y + y/4 - y/100 + y/400) % 7;
  }

  // method overloading (change argument type-list)
  public static int dayOfWeek(int day, int month) {
    return dayOfWeek(day, month, 2019);
  }

  public int dayOfWeek() {
    return dayOfWeek(this.day, this.month, this.year);
  }

  @Override
  public String toString() {
    return "Date: day=" + this.day
        + " month=" + this.month + " year=" + this.year;
  }
}
