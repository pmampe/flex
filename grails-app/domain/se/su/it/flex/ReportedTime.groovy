package se.su.it.flex

class ReportedTime {
  boolean absentAllDay = false
  Calendar calendar
  String comment
  Date dateCreated
  Employee employee
  Date lastUpdated
  int dailyDelta = 0
  int dailyTotal = 0
  int endMinute = 0 // minute within day -> 60 * h + m
  int lunchLength = 30
  int startMinute = 0 // minute within day -> 60 * h + m

  static constraints = {
    comment(nullable: true, blank: true, maxSize: 128)
    calendar(nullable: false)
    dateCreated(nullable: false)
    employee(nullable: false, unique: ['calendar'])
    lastUpdated(nullable: false)
  }

  public String getFormattedEndTime() {
    return "${(endMinute<600)?'0':''}${(int)(endMinute/60)}:${(endMinute%60<10)?'0':''}${(int)(endMinute%60)}"
  }

  public String getFormattedStartTime() {
    return "${(startMinute<600)?'0':''}${(int)(startMinute/60)}:${(startMinute%60<10)?'0':''}${(int)(startMinute%60)}"
  }
}
