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
  int endHour = 0
  int endMinute = 0
  int lunchLength = 30
  int startHour = 0
  int startMinute = 0

  static constraints = {
    comment(nullable: true, blank: true, maxSize: 128)
    calendar(nullable: false)
    dateCreated(nullable: false)
    employee(nullable: false, unique: ['calendar'])
    lastUpdated(nullable: false)
  }
}
