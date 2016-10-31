package se.su.it.flex

class TimeAdjustment {
  Calendar calendar
  String comment
  Date dateCreated
  int delta = 0
  Employee employee
  Date lastUpdated

  static constraints = {
    calendar(nullable: false)
    comment(nullable: true, blank: true)
    dateCreated(nullable: false)
    employee(nullable: false)
    lastUpdated(nullable: false)
  }
}
